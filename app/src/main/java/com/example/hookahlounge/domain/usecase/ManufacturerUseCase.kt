package com.example.hookahlounge.domain.usecase

import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.data.mappers.toDto
import com.example.hookahlounge.data.mappers.toModel
import com.example.hookahlounge.domain.model.Manufacturer
import com.example.hookahlounge.domain.repository.api.ManufacturerRepository
import com.example.hookahlounge.domain.repository.local.ManufacturerDbRepository
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ManufacturerUseCase @Inject constructor(
    private val manufacturerRepository: ManufacturerRepository,
    private val manufacturerDbRepository: ManufacturerDbRepository,
) {

    suspend fun loadManufacturerById(manufacturerId: Long): Flow<HookahResponse<Manufacturer>> {
        return manufacturerDbRepository.getManufacturerById(manufacturerId).map {
            HookahResponse.Success(it.toModel())
        }.onStart {
            val response: HookahResponse<ManufacturerEntity> =
                manufacturerRepository.getManufacturerById(manufacturerId)
            if (response is HookahResponse.Success) {
                manufacturerDbRepository.upsertManufacturer(response.data)
            }
        }
    }

    suspend fun loadManufacturers(): Flow<HookahResponse<List<Manufacturer>>> {
        return manufacturerDbRepository.getManufacturers().map { list ->
            HookahResponse.Success(
                list.map { manufacturer ->
                    manufacturer.toModel()
                }
            )
        }.onStart {
            val response: HookahResponse<List<ManufacturerEntity>> =
                manufacturerRepository.getManufacturer()
            if (response is HookahResponse.Success) {
                manufacturerDbRepository.upsertAll(response.data)
            }
        }
    }

    suspend fun postManufacturer(manufacturer: Manufacturer): HookahResponse<Manufacturer> {

        val res = manufacturerRepository.postManufacturer(manufacturer.toDto())
            .onSuccess {
                manufacturerDbRepository.upsertManufacturer(it)
            }.onError {
                HookahResponse.Error(it.toString())
            }

        return if (res is HookahResponse.Success) {
            HookahResponse.Success(res.data.toModel())
        } else {
            HookahResponse.Error("Unable to post data")
        }
    }

    suspend fun putManufacturer(manufacturer: Manufacturer): HookahResponse<Manufacturer> {
        val res = manufacturerRepository.putManufacturer(manufacturer.toDto())
        return if (res is HookahResponse.Success) {
            manufacturerDbRepository.upsertManufacturer(res.data)
            HookahResponse.Success(res.data.toModel())
        } else {
            return res as HookahResponse.Error
        }
    }
}