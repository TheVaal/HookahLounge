package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.MenuEntity
import kotlinx.coroutines.flow.Flow

interface MenuDbRepository {
    suspend fun upsertMenu(menu: MenuEntity)

    suspend fun upsertAll(list: List<MenuEntity>)

    suspend fun getMenu(menuId: Long): Flow<MenuEntity>

    suspend fun getMenus(): Flow<List<MenuEntity>>
}