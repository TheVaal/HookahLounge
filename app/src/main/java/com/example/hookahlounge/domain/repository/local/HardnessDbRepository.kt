package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.HardnessEntity
import kotlinx.coroutines.flow.Flow

interface HardnessDbRepository {
    suspend fun upsertHardness(hardness: HardnessEntity)

    suspend fun upsertAll(list: List<HardnessEntity>)

    suspend fun getHardness(): Flow<List<HardnessEntity>>

    suspend fun getHardnessById(hardnessId: Long): Flow<HardnessEntity>

}