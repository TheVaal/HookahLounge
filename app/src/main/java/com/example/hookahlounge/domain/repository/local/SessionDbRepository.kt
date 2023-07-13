package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.projection.SessionWithFields
import kotlinx.coroutines.flow.Flow

interface SessionDbRepository {
    fun getSession(id:Long): Flow<SessionEntity>
    fun getSessions(): Flow<List<SessionWithFields>>

    suspend fun upsertSession(session: SessionEntity)
    suspend fun upsertAll(session: List<SessionEntity>)
}