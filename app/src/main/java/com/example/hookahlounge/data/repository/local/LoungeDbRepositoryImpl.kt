package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoungeDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :
    LoungeDbRepository {

    private val loungeDao = loungesDb.getLoungeDao()
    private val tableDao = loungesDb.getTableDao()

    override fun getLounge(id: Long): Flow<LoungeWithTables> {
        return loungeDao.getLoungeById(id)
    }

    override suspend fun upsertLounge(lounge: LoungeWithTables) {
        loungeDao.upsert(lounge.lounge)
        tableDao.upsertAll(lounge.tables)
    }

    override suspend fun upsertLounge(lounge: LoungeEntity) {
        loungeDao.upsert(lounge)
    }

}