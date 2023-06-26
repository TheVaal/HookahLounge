package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.TobaccoEntity
import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import com.example.hookahlounge.domain.repository.local.TobaccoDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TobaccoDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :

    TobaccoDbRepository {
    private val tobaccoDao = loungesDb.getTobaccoDao()

    override suspend fun upsertTobacco(tobacco: TobaccoEntity) {
        tobaccoDao.upsertTobacco(tobacco)
    }

    override suspend fun upsertAll(list: List<TobaccoEntity>) {
        tobaccoDao.upsertAllTobacco(list)
    }

    override suspend fun getTobacco(): Flow<List<TobaccoWithFields>> {
        return tobaccoDao.getTobacco()
    }

    override suspend fun getTobaccoById(tobaccoId: Long): Flow<TobaccoWithFields> {
        return tobaccoDao.getTobaccoById(tobaccoId)
    }
}