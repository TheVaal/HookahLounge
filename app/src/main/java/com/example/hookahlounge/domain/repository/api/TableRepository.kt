package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.TableDto
import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.domain.util.HookahResponse

interface TableRepository {
    suspend fun loadTablesByLoungeId(loungeId: Long): HookahResponse<List<TableEntity>>

    suspend fun loadTableById(tableId: Long): HookahResponse<TableEntity>

    suspend fun postTable(table: TableDto): HookahResponse<TableEntity>

    suspend fun putTable(table: TableDto): HookahResponse<TableEntity>
}