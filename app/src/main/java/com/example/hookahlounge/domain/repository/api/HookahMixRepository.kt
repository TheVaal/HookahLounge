package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.HookahDto
import com.example.hookahlounge.data.dto.datasource.MixDto
import com.example.hookahlounge.data.entity.projection.HookahEntityWithFields
import com.example.hookahlounge.data.entity.projection.MixWithFields
import com.example.hookahlounge.domain.util.HookahResponse

interface HookahMixRepository {

    suspend fun getMixByOrder(orderId: Long) : HookahResponse<List<MixWithFields>>

    suspend fun getMixById(id: Long): HookahResponse<MixWithFields>

    suspend fun putMix(mix: MixDto): HookahResponse<MixWithFields>

    suspend fun postMix(mix: MixDto): HookahResponse<MixWithFields>

    suspend fun putHookah(hookah: HookahDto): HookahResponse<HookahEntityWithFields>

    suspend fun postHookah(hookah: HookahDto): HookahResponse<HookahEntityWithFields>

}