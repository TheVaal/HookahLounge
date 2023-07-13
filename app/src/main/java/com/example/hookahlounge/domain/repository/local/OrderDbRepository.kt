package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import kotlinx.coroutines.flow.Flow

interface OrderDbRepository {
    suspend fun upsertOrder(order: OrderEntity)

    suspend fun upsertAll(list: List<OrderEntity>)

    suspend fun getOrder(orderId: Long): Flow<OrderWithFields>

}