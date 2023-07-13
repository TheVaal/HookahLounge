package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.domain.repository.local.OrderDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderDbRepositoryImpl @Inject constructor(orderDb: HookahLoungeDatabase) : OrderDbRepository {
    private val orderDao = orderDb.getOrderDao()
    override suspend fun upsertOrder(order: OrderEntity) {
        orderDao.upsertOrder(order)
    }

    override suspend fun upsertAll(list: List<OrderEntity>) {
        orderDao.upsertAll(list)
    }

    override suspend fun getOrder(orderId: Long): Flow<OrderWithFields> {
        return orderDao.getOrder(orderId)
    }

}