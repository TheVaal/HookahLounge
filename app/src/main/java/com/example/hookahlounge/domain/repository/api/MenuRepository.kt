package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.MenuDto
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.domain.util.HookahResponse

interface MenuRepository {

    suspend fun loadMenus(): HookahResponse<List<MenuEntity>>

    suspend fun loadMenuById(menuId: Long): HookahResponse<MenuEntity>

    suspend fun putMenu(menu: MenuDto): HookahResponse<MenuEntity>

    suspend fun postMenu(menu: MenuDto): HookahResponse<MenuEntity>
}