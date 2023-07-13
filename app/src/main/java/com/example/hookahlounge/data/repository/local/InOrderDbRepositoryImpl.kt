package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import com.example.hookahlounge.domain.repository.local.InOrderDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InOrderDbRepositoryImpl@Inject constructor(orderDb: HookahLoungeDatabase) : InOrderDbRepository {
    private val inOrderDao = orderDb.getInOrderDao()

    override suspend fun upsertInOrder(inOrder: InOrderEntity) {
        inOrderDao.upsertInOrder(inOrder)
    }

    override suspend fun upsertAll(list: List<InOrderEntity>) {
        inOrderDao.upsertAll(list)
    }

    override suspend fun getInOrder(orderId: Long): Flow<List<InOrderWithFields>> {
        return inOrderDao.getInOrder(orderId)
    }
}