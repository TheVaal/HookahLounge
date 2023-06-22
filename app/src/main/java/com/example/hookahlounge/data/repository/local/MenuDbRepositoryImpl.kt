package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.domain.repository.local.MenuDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuDbRepositoryImpl@Inject constructor(menuDb: HookahLoungeDatabase) : MenuDbRepository {
    private val menuDao = menuDb.getMenuDao()

    override suspend fun upsertMenu(menu: MenuEntity) {
        menuDao.upsertMenu(menu)
    }

    override suspend fun upsertAll(list: List<MenuEntity>) {
        menuDao.upsertAllMenus(list)
    }

    override suspend fun getMenu(menuId: Long): Flow<MenuEntity> {
        return menuDao.getMenuById(menuId)
    }

    override suspend fun getMenus(): Flow<List<MenuEntity>> {
        return menuDao.getMenu()
    }
}