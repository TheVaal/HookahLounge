package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.util.HookahResponse

interface HardnessRepository {
    suspend fun getHardness(): HookahResponse<List<Hardness>>

    suspend fun getHardness(id: Int): HookahResponse<Hardness>

    suspend fun patchHardness(id: Int, hardness: Hardness): HookahResponse<Hardness>

    suspend fun postHardness(id: Int, hardness: Hardness): HookahResponse<Hardness>

}