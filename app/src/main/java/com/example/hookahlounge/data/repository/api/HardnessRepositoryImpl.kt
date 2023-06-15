package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.repository.api.HardnessRepository
import com.example.hookahlounge.domain.util.HookahResponse
import javax.inject.Inject

class HardnessRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
): HardnessRepository {
    override suspend fun getHardness(): HookahResponse<List<Hardness>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHardness(id: Int): HookahResponse<Hardness> {
        TODO("Not yet implemented")
    }

    override suspend fun patchHardness(id: Int, hardness: Hardness): HookahResponse<Hardness> {
        TODO("Not yet implemented")
    }

    override suspend fun postHardness(id: Int, hardness: Hardness): HookahResponse<Hardness> {
        TODO("Not yet implemented")
    }
}