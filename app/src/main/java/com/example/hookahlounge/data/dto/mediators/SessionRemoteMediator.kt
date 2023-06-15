package com.example.hookahlounge.data.dto.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.mappers.toSessionEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SessionRemoteMediator(
    private val sessionDb: HookahLoungeDatabase,
    private val sessionApi: HookahLoungeApi
): RemoteMediator<Int, SessionEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SessionEntity>,
    ): MediatorResult {
        return try {
            val loadKey: Int = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    } else {
                        (lastItem.id / state.config.pageSize).toInt() + 1
                    }
                }
            }
            val response = sessionApi.getSessions(
                page = loadKey,
                pageSize = state.config.pageSize
            )

            if (response.isSuccessful){
                val sessions = response.body()!!.data

                sessionDb.withTransaction {

                    val orderEntities = sessions.map {
                        it.toSessionEntity()
                    }
                    sessionDb.getSessionDao().upsertAll(orderEntities)
                }
                MediatorResult.Success(endOfPaginationReached = sessions.size < state.config.pageSize)
            } else {
                MediatorResult.Error(Throwable(response.errorBody().toString()))
            }
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        } catch(e: IOException) {
            MediatorResult.Error(e)
        }
    }
}