package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.domain.model.Hookah
import com.example.hookahlounge.domain.repository.api.HookahRepository
import com.example.hookahlounge.domain.util.HookahResponse
import javax.inject.Inject

class HookahRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : HookahRepository {
    override suspend fun getHookahs(): HookahResponse<List<Hookah>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHookah(id: Int): HookahResponse<Hookah> {
        TODO("Not yet implemented")
    }

    override suspend fun patchHookah(id: Int, hookah: Hookah): HookahResponse<Hookah> {
        TODO("Not yet implemented")
    }

    override suspend fun postHookah(id: Int, hookah: Hookah): HookahResponse<Hookah> {
        TODO("Not yet implemented")
    }
}