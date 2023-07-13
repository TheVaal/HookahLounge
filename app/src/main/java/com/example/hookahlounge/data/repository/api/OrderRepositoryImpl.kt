package com.example.hookahlounge.data.repository.api

import androidx.datastore.core.DataStore
import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.OrderDto
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.data.mappers.toOrderWithFields
import com.example.hookahlounge.domain.repository.api.OrderRepository
import com.example.hookahlounge.domain.util.HookahResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
    private val dataStore: DataStore<UserPreference>,
) :
    OrderRepository {
    override suspend fun getOrder(id: Long): HookahResponse<OrderWithFields> {
        return try {
            val response = api.getOrder(
                id,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toOrderWithFields())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: Exception) {
            HookahResponse.Error(e.message.toString())
        }
    }

    override suspend fun putOrder(order: OrderDto): HookahResponse<OrderWithFields> {
        val response = api.putOrder(
            order.id,
            order,
            "Bearer ${dataStore.data.first().token}"
        )
        return if (response.isSuccessful) {
            HookahResponse.Success(order.toOrderWithFields())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postOrder(order: OrderDto): HookahResponse<OrderWithFields> {
        return try {
            val response = api.postOrder(
                order,
                "Bearer ${dataStore.data.first().token}"
            )
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toOrderWithFields())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

}