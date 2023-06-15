package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.domain.repository.local.TableDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TableDbRepositoryImpl @Inject constructor(database: HookahLoungeDatabase) : TableDbRepository {
    private val tableDao = database.getTableDao()

    override suspend fun upsertTable(table: TableEntity) {
        tableDao.upsert(table)
    }

    override suspend fun getTable(id: Long): Flow<TableEntity> {
        return tableDao.getTable(id)
    }
}