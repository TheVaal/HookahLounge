package com.example.hookahlounge.domain.repository.local

import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import kotlinx.coroutines.flow.Flow

interface ManufacturerDbRepository {
    suspend fun upsertManufacturer(manufacturer: ManufacturerEntity)

    suspend fun upsertAll(list: List<ManufacturerEntity>)

    suspend fun getManufacturers(): Flow<List<ManufacturerEntity>>

    suspend fun getManufacturerById(manufacturerId: Long): Flow<ManufacturerEntity>

}