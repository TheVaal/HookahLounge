package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.HardnessDto
import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.domain.util.HookahResponse

interface HardnessRepository {
    suspend fun getHardness(): HookahResponse<List<HardnessEntity>>

    suspend fun getHardnessById(id: Long): HookahResponse<HardnessEntity>

    suspend fun putHardness(hardness: HardnessDto): HookahResponse<HardnessEntity>

    suspend fun postHardness(hardness: HardnessDto): HookahResponse<HardnessEntity>

}