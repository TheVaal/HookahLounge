package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import com.example.hookahlounge.domain.repository.local.LoungeMenuDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoungeMenuDbRepositoryImpl@Inject constructor(menuDb: HookahLoungeDatabase) : LoungeMenuDbRepository {
    private val menuDao = menuDb.getMenuDao()

    override suspend fun upsertLoungeMenu(loungeMenu: LoungeMenuEntity) {
        menuDao.upsertLoungeMenu(loungeMenu)
    }

    override suspend fun upsertAll(list: List<LoungeMenuEntity>) {
        menuDao.upsertAllLoungeMenus(list)
    }

    override suspend fun getLoungeMenu(loungeId: Long): Flow<List<LoungeMenuWithFields>> {
        return menuDao.getLoungeMenuByLounge(loungeId)
    }

    override suspend fun getLoungeMenuById(loungeMenuId: Long): Flow<LoungeMenuWithFields> {
        return menuDao.getLoungeMenuById(loungeMenuId)
    }

}