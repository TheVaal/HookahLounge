package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.domain.repository.local.HardnessDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HardnessDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :
    HardnessDbRepository {
    private val hardnessDao = loungesDb.getHardnessDao()
    override suspend fun upsertHardness(hardness: HardnessEntity) {
        hardnessDao.upsertHardness(hardness)
    }

    override suspend fun upsertAll(list: List<HardnessEntity>) {
        hardnessDao.upsertAllHardness(list)
    }

    override suspend fun getHardness(): Flow<List<HardnessEntity>> {
        return hardnessDao.getHardness()
    }

    override suspend fun getHardnessById(hardnessId: Long): Flow<HardnessEntity> {
        return hardnessDao.getHardnessById(hardnessId)
    }
}