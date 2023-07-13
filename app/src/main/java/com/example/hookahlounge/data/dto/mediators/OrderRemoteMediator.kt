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
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.data.mappers.toOrderEntity
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class OrderRemoteMediator(
    private val orderDb: HookahLoungeDatabase,
    private val orderApi: HookahLoungeApi,
    private val tableRepository: TableRepository,
    private val sessionRepository: SessionRepository,
    private val userDataStore: DataStore<UserPreference>,
) : RemoteMediator<Int, OrderWithFields>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OrderWithFields>,
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
                        (lastItem.order.id / state.config.pageSize).toInt() + 1
                    }
                }
            }
            val user = userDataStore.data.first()
            val response = orderApi.getOrders(
                page = loadKey,
                pageSize = state.config.pageSize,
                auth = "Bearer ${user.token}"
            )

            if (response.isSuccessful) {
                val orders = response.body()!!.data

                orderDb.withTransaction {
//                    if (loadType == LoadType.REFRESH) {
//                        orderDb.getOrderDao().clearAll()
//                    }
                    val orderEntities = orders.map {
                        tableRepository.loadTableById(it.tableId).onSuccess { table ->
                            orderDb.getTableDao().upsert(table)
                        }

                        sessionRepository.getSession(it.sessionId).onSuccess { session ->
                            orderDb.getSessionDao().upsert(session)
                        }
                        it.toOrderEntity()
                    }
                    orderDb.getOrderDao().upsertAll(orderEntities)
                }
                MediatorResult.Success(endOfPaginationReached = orders.size < state.config.pageSize)
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