package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.SessionDto
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.domain.util.HookahResponse

interface SessionRepository {
    suspend fun getSession(id: Long): HookahResponse<SessionEntity>

    suspend fun putSession(session: SessionDto): HookahResponse<SessionEntity>

    suspend fun postSession(session: SessionDto): HookahResponse<SessionEntity>

}