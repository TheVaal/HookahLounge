package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import kotlinx.coroutines.flow.Flow

interface LoungeMenuDbRepository {
    suspend fun upsertLoungeMenu(loungeMenu: LoungeMenuEntity)

    suspend fun upsertAll(list: List<LoungeMenuEntity>)

    suspend fun getLoungeMenu(loungeId: Long): Flow<List<LoungeMenuWithFields>>

    suspend fun getLoungeMenuById(loungeMenuId: Long): Flow<LoungeMenuWithFields>

}