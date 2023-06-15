package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.TableEntity
import kotlinx.coroutines.flow.Flow

interface TableDbRepository {

    suspend fun upsertTable(table: TableEntity)
    suspend fun getTable(id: Long): Flow<TableEntity>
}