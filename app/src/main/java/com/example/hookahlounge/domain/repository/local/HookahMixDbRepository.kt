package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.MixEntity
import com.example.hookahlounge.data.entity.projection.MixWithFields
import kotlinx.coroutines.flow.Flow

interface HookahMixDbRepository {
    suspend fun upsertHookah(hookah: HookahEntity)

    suspend fun upsertHookahs(list: List<HookahEntity>)

    suspend fun getMix(orderId: Long): Flow<List<MixWithFields>>

    suspend fun getMixById(mixId: Long): Flow<MixWithFields>

    suspend fun upsertMix(mix: MixEntity)

    suspend fun upsertMixes(list: List<MixWithFields>)


}