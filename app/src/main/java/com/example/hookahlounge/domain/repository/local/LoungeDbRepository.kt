package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import kotlinx.coroutines.flow.Flow

interface LoungeDbRepository {
    fun getLounge(id:Long): Flow<LoungeWithTables>

    suspend fun upsertLounge(lounge: LoungeWithTables)

    suspend fun upsertLounge(lounge: LoungeEntity)
}