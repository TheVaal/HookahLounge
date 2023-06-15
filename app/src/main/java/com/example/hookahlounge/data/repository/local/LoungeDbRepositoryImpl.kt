package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.data.mappers.toLounge
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoungeDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :
    LoungeDbRepository {

    private val loungeDao = loungesDb.getLoungeDao()
    private val tableDao = loungesDb.getTableDao()

    override fun getLounge(id: Long): Flow<Lounge> {
        return loungeDao.getLoungeById(id).map {
            it.toLounge()
        }
    }

    override suspend fun upsertLounge(lounge: LoungeWithTables) {
        loungeDao.upsert(lounge.lounge)
        tableDao.upsertAll(lounge.tables)
    }

    override suspend fun upsertLounge(lounge: LoungeEntity) {
        loungeDao.upsert(lounge)
    }

}