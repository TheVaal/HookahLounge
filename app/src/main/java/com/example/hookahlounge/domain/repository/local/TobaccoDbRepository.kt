package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.TobaccoEntity
import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import kotlinx.coroutines.flow.Flow

interface TobaccoDbRepository {
    suspend fun upsertTobacco(tobacco: TobaccoEntity)

    suspend fun upsertAll(list: List<TobaccoEntity>)

    suspend fun getTobacco(): Flow<List<TobaccoWithFields>>

    suspend fun getTobaccoById(tobaccoId: Long): Flow<TobaccoWithFields>

}