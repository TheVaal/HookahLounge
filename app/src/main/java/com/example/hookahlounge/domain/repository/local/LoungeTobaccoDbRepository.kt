package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import kotlinx.coroutines.flow.Flow

interface LoungeTobaccoDbRepository {
    suspend fun upsertLoungeTobacco(loungeTobacco: LoungeTobaccoEntity)

    suspend fun upsertAll(list: List<LoungeTobaccoEntity>)

    suspend fun getTobacco(loungeId: Long): Flow<List<LoungeTobaccoWithFields>>

    suspend fun getTobaccoById(loungeTobaccoId: Long): Flow<LoungeTobaccoWithFields>

}