package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.projection.SessionWithFields
import com.example.hookahlounge.domain.repository.local.SessionDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionDbRepositoryImpl @Inject constructor(sessionDb: HookahLoungeDatabase) :
    SessionDbRepository {
    private val sessionDao = sessionDb.getSessionDao()

    override fun getSession(id: Long): Flow<SessionEntity> {
        return sessionDao.getSessionById(id)
    }

    override fun getSessions(): Flow<List<SessionWithFields>> {
        return sessionDao.getSessions()
    }

    override suspend fun upsertSession(session: SessionEntity) {
        sessionDao.upsert(session)
    }

    override suspend fun upsertAll(session: List<SessionEntity>) {
        sessionDao.upsertAll(session)
    }

}