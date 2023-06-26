package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.TobaccoDto
import com.example.hookahlounge.data.entity.core.TobaccoEntity
import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import com.example.hookahlounge.domain.util.HookahResponse

interface TobaccoRepository {
    suspend fun getTobacco(): HookahResponse<List<TobaccoWithFields>>

    suspend fun getTobaccoById(id: Long): HookahResponse<TobaccoWithFields>

    suspend fun putTobacco(tobacco: TobaccoDto): HookahResponse<TobaccoWithFields>

    suspend fun postTobacco(tobacco: TobaccoDto): HookahResponse<TobaccoWithFields>

}