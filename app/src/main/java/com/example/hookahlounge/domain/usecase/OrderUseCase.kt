package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toOrder
import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.domain.repository.api.OrderRepository
import com.example.hookahlounge.domain.repository.local.OrderDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class OrderUseCase@Inject constructor(
    private val orderRepository: OrderRepository,
    private val orderDbRepository: OrderDbRepository
) {
    suspend fun loadOrderById(orderId: Long): Flow<HookahResponse<Order>> {
        return orderDbRepository.getOrder(orderId).map {
            HookahResponse.Success(it.toOrder())
        }.onStart {
            val response: HookahResponse<OrderWithFields> = orderRepository.getOrder(orderId)
            if (response is HookahResponse.Success) {
                orderDbRepository.upsertOrder(response.data.order)
            }
        }
    }

    suspend fun postOrder(order: Order): HookahResponse<Order> {

        val res = orderRepository.postOrder(order.toDto())
            .onSuccess {
                orderDbRepository.upsertOrder(it.order)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toOrder())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putOrder(order: Order): HookahResponse<Order> {
        val res = orderRepository.putOrder(order.toDto())
        return if (res is HookahResponse.Success) {
            orderDbRepository.upsertOrder(res.data.order)
            HookahResponse.Success(res.data.toOrder())
        } else {
            return res as HookahResponse.Error
        }
    }
}