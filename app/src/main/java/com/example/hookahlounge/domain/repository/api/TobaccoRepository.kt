package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.domain.model.Tobacco
import com.example.hookahlounge.domain.util.HookahResponse

interface TobaccoRepository {
    suspend fun getTobacco(): HookahResponse<List<Tobacco>>

    suspend fun getTobacco(id: Int): HookahResponse<Tobacco>

    suspend fun patchTobacco(id: Int, tobacco: Tobacco): HookahResponse<Tobacco>

    suspend fun postTobacco(id: Int, tobacco: Tobacco): HookahResponse<Tobacco>

}