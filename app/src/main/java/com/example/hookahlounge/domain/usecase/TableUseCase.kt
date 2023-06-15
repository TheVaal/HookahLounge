package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toTable
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.repository.local.TableDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TableUseCase @Inject constructor(
    private val tableDbRepository: TableDbRepository,
    private val tableRepository: TableRepository,
) {

    suspend fun getTable(id: Long): Flow<HookahResponse<Table>> {
        return tableDbRepository.getTable(id).map {
            HookahResponse.Success(it.toTable())
        }.onStart {
            val response: HookahResponse<TableEntity> = tableRepository.loadTableById(id)
            if (response is HookahResponse.Success) {
                tableDbRepository.upsertTable(response.data)
            }
        }

    }

    suspend fun postTable(table: Table): HookahResponse<Table> {

        val res = tableRepository.postTable(table.toDto()).onSuccess {
            tableDbRepository.upsertTable(it)
        }.onError {
            HookahResponse.Error(it.toString())
        }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toTable())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putTable(table: Table): HookahResponse<Table> {
        val res = tableRepository.putTable(table.toDto())
        return if (res is HookahResponse.Success){
            tableDbRepository.upsertTable(res.data)
            HookahResponse.Success(res.data.toTable())
        } else {
            return res as HookahResponse.Error
        }
    }
}