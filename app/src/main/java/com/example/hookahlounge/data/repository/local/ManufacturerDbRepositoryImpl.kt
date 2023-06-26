package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.domain.repository.local.ManufacturerDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManufacturerDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :
    ManufacturerDbRepository {
    private val manufacturerDao = loungesDb.getManufacturerDao()
    override suspend fun upsertManufacturer(manufacturer: ManufacturerEntity) {
        manufacturerDao.upsertManufacturer(manufacturer)
    }

    override suspend fun upsertAll(list: List<ManufacturerEntity>) {
        manufacturerDao.upsertAllManufacturers(list)
    }

    override suspend fun getManufacturers(): Flow<List<ManufacturerEntity>> {
        return manufacturerDao.getManufacturers()
    }

    override suspend fun getManufacturerById(manufacturerId: Long): Flow<ManufacturerEntity> {
        return manufacturerDao.getManufacturerById(manufacturerId)
    }

}