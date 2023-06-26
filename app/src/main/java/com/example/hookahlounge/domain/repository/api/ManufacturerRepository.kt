package com.example.hookahlounge.domain.repository.api

import com.example.hookahlounge.data.dto.datasource.ManufacturerDto
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.domain.util.HookahResponse

interface ManufacturerRepository {
    suspend fun getManufacturer(): HookahResponse<List<ManufacturerEntity>>

    suspend fun getManufacturerById(id: Long): HookahResponse<ManufacturerEntity>

    suspend fun putManufacturer(manufacturer: ManufacturerDto): HookahResponse<ManufacturerEntity>

    suspend fun postManufacturer(manufacturer: ManufacturerDto): HookahResponse<ManufacturerEntity>

}