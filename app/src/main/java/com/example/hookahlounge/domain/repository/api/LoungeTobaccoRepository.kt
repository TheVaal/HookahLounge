package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.LoungeTobaccoDto
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import com.example.hookahlounge.domain.util.HookahResponse

interface LoungeTobaccoRepository  {
    suspend fun getLoungeTobacco(loungeId: Long): HookahResponse<List<LoungeTobaccoWithFields>>

    suspend fun getLoungeTobaccoById(id: Long): HookahResponse<LoungeTobaccoWithFields>

    suspend fun putLoungeTobacco(loungeTobacco: LoungeTobaccoDto): HookahResponse<LoungeTobaccoWithFields>

    suspend fun postLoungeTobacco(loungeTobacco: LoungeTobaccoDto): HookahResponse<LoungeTobaccoWithFields>

}