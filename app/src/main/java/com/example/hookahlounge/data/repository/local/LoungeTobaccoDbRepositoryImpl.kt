package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import com.example.hookahlounge.domain.repository.local.LoungeTobaccoDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoungeTobaccoDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :
    LoungeTobaccoDbRepository {
    private val tobaccoDao = loungesDb.getTobaccoDao()

    override suspend fun upsertLoungeTobacco(loungeTobacco: LoungeTobaccoEntity) {
        tobaccoDao.upsertLoungeTobacco(loungeTobacco)
    }

    override suspend fun upsertAll(list: List<LoungeTobaccoEntity>) {
        tobaccoDao.upsertAllLoungeTobaccos(list)
    }

    override suspend fun getTobacco(loungeId: Long): Flow<List<LoungeTobaccoWithFields>> {
        return tobaccoDao.getLoungeTobaccoByLounge(loungeId)
    }

    override suspend fun getTobaccoById(loungeTobaccoId: Long): Flow<LoungeTobaccoWithFields> {
        return tobaccoDao.getLoungeTobaccoById(loungeTobaccoId)
    }
}