package com.example.hookahlounge.domain.usecase


import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toSession
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.repository.local.SessionDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SessionUseCase @Inject constructor(
    val sessionRepository: SessionRepository,
    val sessionDbRepository: SessionDbRepository,
) {
    fun loadSessionById(SessionId: Long): Flow<HookahResponse<Session>> {
        return sessionDbRepository.getSession(SessionId).map {
            HookahResponse.Success(it.toSession())
        }.onStart {
            val response: HookahResponse<SessionEntity> = sessionRepository.getSession(SessionId)
            if (response is HookahResponse.Success) {
                sessionDbRepository.upsertSession(response.data)
            }
        }
    }

    fun loadSessions(): Flow<HookahResponse<List<Session>>> {
        return sessionDbRepository.getSessions().map {
            HookahResponse.Success(
                it.map { session ->
                    session.toSession()
                }
            )
        }.onStart {
            val response: HookahResponse<List<SessionEntity>> = sessionRepository.getSessions()
            if (response is HookahResponse.Success) {
                sessionDbRepository.upsertAll(response.data)
            }
        }
    }

    suspend fun postSession(Session: Session): HookahResponse<Session> {

        val res = sessionRepository.postSession(Session.toDto())
            .onSuccess {
                sessionDbRepository.upsertSession(it)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toSession())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putSession(Session: Session): HookahResponse<Session> {
        val res = sessionRepository.putSession(Session.toDto())
        return if (res is HookahResponse.Success) {
            sessionDbRepository.upsertSession(res.data)
            HookahResponse.Success(res.data.toSession())
        } else {
            return res as HookahResponse.Error
        }
    }
}