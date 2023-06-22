package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.LoungeMenuDto
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import com.example.hookahlounge.domain.util.HookahResponse

interface LoungeMenuRepository {
    suspend fun loadLoungeMenuByLoungeId(loungeId: Long): HookahResponse<List<LoungeMenuWithFields>>

    suspend fun loadLoungeMenuById(loungeMenuId: Long): HookahResponse<LoungeMenuWithFields>

    suspend fun putLoungeMenu(loungeMenu: LoungeMenuDto): HookahResponse<LoungeMenuWithFields>

    suspend fun postLoungeMenu(loungeMenu: LoungeMenuDto): HookahResponse<LoungeMenuWithFields>
}