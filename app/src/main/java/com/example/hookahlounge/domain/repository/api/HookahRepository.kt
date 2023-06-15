package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.domain.model.Hookah
import com.example.hookahlounge.domain.util.HookahResponse

interface HookahRepository {
    suspend fun getHookahs(): HookahResponse<List<Hookah>>

    suspend fun getHookah(id: Int): HookahResponse<Hookah>

    suspend fun patchHookah(id: Int, hookah: Hookah): HookahResponse<Hookah>

    suspend fun postHookah(id: Int, hookah: Hookah): HookahResponse<Hookah>

}