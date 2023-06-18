package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.SessionEntity
import kotlinx.coroutines.flow.Flow

interface SessionDbRepository {
    fun getSession(id:Long): Flow<SessionEntity>

    suspend fun upsertSession(session: SessionEntity)
}