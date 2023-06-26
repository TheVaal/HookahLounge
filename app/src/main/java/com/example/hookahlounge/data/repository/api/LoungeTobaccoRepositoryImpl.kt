package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.LoungeTobaccoDto
import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import com.example.hookahlounge.data.mappers.toEntityWithFields
import com.example.hookahlounge.domain.repository.api.LoungeTobaccoRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class LoungeTobaccoRepositoryImpl @Inject constructor(
    private val api: HookahLoungeApi,
) : LoungeTobaccoRepository {

    override suspend fun getLoungeTobacco(loungeId: Long): HookahResponse<List<LoungeTobaccoWithFields>> {
        return try {
            val tobacco = mutableListOf<LoungeTobaccoDto>()
            loadPage(
                addAll = { responseData: List<LoungeTobaccoDto> ->
                    tobacco.addAll(responseData)
                },
                loungeId = loungeId
            )
            HookahResponse.Success(data = tobacco.map {
                it.toEntityWithFields()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    private suspend fun loadPage(
        addAll: (List<LoungeTobaccoDto>) -> (Unit),
        loungeId: Long,
        currentPage: Int = 1,
    ) {
        val response = api.getLoungeTobacco(currentPage, loungeId)
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

    override suspend fun getLoungeTobaccoById(id: Long): HookahResponse<LoungeTobaccoWithFields> {
        return try {
            val response = api.getLoungeTobaccoById(id)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putLoungeTobacco(
        loungeTobacco: LoungeTobaccoDto,
    ): HookahResponse<LoungeTobaccoWithFields> {
        val response = api.putLoungeTobacco(loungeTobacco.id, loungeTobacco)
        return try {
            if (response.isSuccessful) {
                HookahResponse.Success(loungeTobacco.toEntityWithFields())
            } else {
                HookahResponse.Error("Put data error")
            }
        } catch (e: Exception) {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postLoungeTobacco(loungeTobacco: LoungeTobaccoDto): HookahResponse<LoungeTobaccoWithFields> {
        return try {
            val response = api.postLoungeTobacco(loungeTobacco)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntityWithFields())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }
}