package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.TobaccoDto
import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import com.example.hookahlounge.data.mappers.toEntityWithFields
import com.example.hookahlounge.domain.repository.api.TobaccoRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class TobaccoRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : TobaccoRepository {
    override suspend fun getTobacco(): HookahResponse<List<TobaccoWithFields>> {
        return try {
            val tobacco = mutableListOf<TobaccoDto>()
            loadPage(
                addAll = { responseData: List<TobaccoDto> ->
                    tobacco.addAll(responseData)
                }
            )
            HookahResponse.Success(data = tobacco.map {
                it.toEntityWithFields()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }
    private suspend fun loadPage(
        addAll: (List<TobaccoDto>) -> (Unit),
        currentPage: Int = 1
    ) {
        val response = api.getTobacco(currentPage)
        if (response.isSuccessful) {
            val responseMetadata = response.body()!!.meta
            addAll(response.body()!!.data)
            if (responseMetadata.currentPage!! < responseMetadata.lastPage!!) {
                loadPage(
                    addAll = addAll,
                    currentPage = currentPage.plus(1)
                )
            }
        }
    }

    override suspend fun getTobaccoById(id: Long): HookahResponse<TobaccoWithFields> {
        return try {
            val response = api.getTobaccoById(id)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putTobacco(tobacco: TobaccoDto): HookahResponse<TobaccoWithFields> {
        val response = api.putTobacco(tobacco.id, tobacco)
        return try {
            if (response.isSuccessful){
                HookahResponse.Success(tobacco.toEntityWithFields())
            } else {
                HookahResponse.Error("Put data error")
            }
        } catch (e: Exception) {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postTobacco(tobacco: TobaccoDto): HookahResponse<TobaccoWithFields> {
        return try {
            val response = api.postTobacco(tobacco)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }
}