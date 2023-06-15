package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.domain.model.Tobacco
import com.example.hookahlounge.domain.repository.api.TobaccoRepository
import com.example.hookahlounge.domain.util.HookahResponse
import javax.inject.Inject

class TobaccoRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : TobaccoRepository {
    override suspend fun getTobacco(): HookahResponse<List<Tobacco>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTobacco(id: Int): HookahResponse<Tobacco> {
        TODO("Not yet implemented")
    }

    override suspend fun patchTobacco(id: Int, tobacco: Tobacco): HookahResponse<Tobacco> {
        TODO("Not yet implemented")
    }

    override suspend fun postTobacco(id: Int, tobacco: Tobacco): HookahResponse<Tobacco> {
        TODO("Not yet implemented")
    }
}