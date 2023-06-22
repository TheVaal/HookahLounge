package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.TableDto
import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.data.mappers.toTableEntity
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class TableRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : TableRepository {
    override suspend fun loadTablesByLoungeId(loungeId: Long): HookahResponse<List<TableEntity>> {

        return try {
            val tables = mutableListOf<TableDto>()
            loadPage(
                addAll = { responseData: List<TableDto> ->
                    tables.addAll(responseData)
                },
                loungeId = loungeId
            )
            HookahResponse.Success(data = tables.map {
                it.toTableEntity()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    private suspend fun loadPage(
        addAll: (List<TableDto>) -> (Unit),
        currentPage: Int = 1,
        loungeId: Long,
    ) {
        val response = api.getTables(currentPage, loungeId)
        if (response.isSuccessful) {
            val responseMetadata = response.body()!!.meta
            addAll(response.body()!!.data)
            if (responseMetadata.currentPage!! < responseMetadata.lastPage!!) {
                loadPage(
                    addAll = addAll,
                    currentPage = currentPage.plus(1),
                    loungeId = loungeId
                )
            }
        }
    }

    override suspend fun loadTableById(tableId: Long): HookahResponse<TableEntity> {
        return try {
            val response = api.getTable(tableId)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toTableEntity())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun postTable(table: TableDto): HookahResponse<TableEntity> {
        return try {
            val response = api.postTable(table)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toTableEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putTable(table: TableDto): HookahResponse<TableEntity> {
        val response = api.putTable(table.id, table)
        return if (response.isSuccessful){
            HookahResponse.Success(table.toTableEntity())
        } else {
            HookahResponse.Error("Put data error")
        }
    }
}