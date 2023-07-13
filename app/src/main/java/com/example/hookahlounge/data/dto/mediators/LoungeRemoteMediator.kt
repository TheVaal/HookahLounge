package com.example.hookahlounge.data.dto.mediators

import androidx.datastore.core.DataStore
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.mappers.toLoungeEntity
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class LoungeRemoteMediator(
    private val loungeDb: HookahLoungeDatabase,
    private val loungeApi: HookahLoungeApi,
    private val userDataStore: DataStore<UserPreference>,
) : RemoteMediator<Int, LoungeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LoungeEntity>,
    ): MediatorResult {
        return try {
            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize).toInt() + 1
                    }
                }
            }
            val user = userDataStore.data.first()
            val response = loungeApi.getLounges(
                page = loadKey,
                pageSize = state.config.pageSize,
                auth = "Bearer ${user.token}"
            )

            if (response.isSuccessful) {
                val lounges = loungeApi.getLounges(
                    page = loadKey,
                    pageSize = state.config.pageSize,
                    auth = "Bearer ${user.token}"
                ).body()!!.data

                loungeDb.withTransaction {
//                    if (loadType == LoadType.REFRESH) {
//                        loungeDb.getLoungeDao().clearAll()
//                    }
                    val loungeEntities = lounges.map {
                        it.toLoungeEntity()
                    }
                    loungeDb.getLoungeDao().upsertAll(loungeEntities)
                }
                MediatorResult.Success(endOfPaginationReached = lounges.size < state.config.pageSize)
            } else {
                MediatorResult.Error(Throwable(response.errorBody().toString()))
            }
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}