package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.OrderDto
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.domain.util.HookahResponse

interface OrderRepository {
    suspend fun getOrder(id: Long): HookahResponse<OrderWithFields>

    suspend fun putOrder(order: OrderDto): HookahResponse<OrderWithFields>

    suspend fun postOrder(order: OrderDto): HookahResponse<OrderWithFields>

}