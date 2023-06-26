package com.example.hookahlounge.data.repository.api

import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.datasource.ManufacturerDto
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.data.mappers.toEntity
import com.example.hookahlounge.domain.repository.api.ManufacturerRepository
import com.example.hookahlounge.domain.util.HookahResponse
import retrofit2.HttpException
import javax.inject.Inject

class ManufacturerRepositoryImpl@Inject constructor(
    private val api: HookahLoungeApi,
): ManufacturerRepository {
    override suspend fun getManufacturer(): HookahResponse<List<ManufacturerEntity>> {
        return try {
            val manufacturers = mutableListOf<ManufacturerDto>()
            loadPage(
                addAll = { responseData: List<ManufacturerDto> ->
                    manufacturers.addAll(responseData)
                }
            )
            HookahResponse.Success(data = manufacturers.map {
                it.toEntity()
            })

        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    private suspend fun loadPage(
        addAll: (List<ManufacturerDto>) -> (Unit),
        currentPage: Int = 1
    ) {
        val response = api.getManufacturer(currentPage)
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

    override suspend fun getManufacturerById(id: Long): HookahResponse<ManufacturerEntity> {
        return try {
            val response = api.getManufacturerById(id)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntity())
            } else {
                HookahResponse.Error("Unable to reach api host")
            }
        } catch (e: HttpException) {
            HookahResponse.Error(e.message())
        }
    }

    override suspend fun putManufacturer(
        manufacturer: ManufacturerDto,
    ): HookahResponse<ManufacturerEntity> {
        val response = api.putManufacturer(manufacturer.id, manufacturer)
        return if (response.isSuccessful){
            HookahResponse.Success(manufacturer.toEntity())
        } else {
            HookahResponse.Error("Put data error")
        }
    }

    override suspend fun postManufacturer(manufacturer: ManufacturerDto): HookahResponse<ManufacturerEntity> {
        return try {
            val response = api.postManufacturer(manufacturer)
            if (response.isSuccessful) {
                HookahResponse.Success(response.body()!!.data.toEntity())
            } else {
                HookahResponse.Error("Unable to post new table")
            }
        } catch (e: HttpException){
            HookahResponse.Error(e.message())
        }
    }
}