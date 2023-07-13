package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import kotlinx.coroutines.flow.Flow

interface InOrderDbRepository {

    suspend fun upsertInOrder(inOrder: InOrderEntity)

    suspend fun upsertAll(list: List<InOrderEntity>)

    suspend fun getInOrder(orderId: Long): Flow<List<InOrderWithFields>>

}