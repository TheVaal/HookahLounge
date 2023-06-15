package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.LoungeDto
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.domain.util.HookahResponse

interface LoungeRepository {
    suspend fun getLounges(): HookahResponse<List<LoungeEntity>>

    suspend fun getLounge(id: Long): HookahResponse<LoungeWithTables>

    suspend fun putLounge(lounge: LoungeDto): HookahResponse<LoungeEntity>

    suspend fun postLounge(lounge: LoungeDto): HookahResponse<LoungeEntity>

}