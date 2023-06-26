package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ManufacturerDao {
    @Upsert
    suspend fun upsertAllManufacturers(loungeManufacturers: List<ManufacturerEntity>)

    @Upsert
    suspend fun upsertManufacturer(manufacturer: ManufacturerEntity)

    @Query("SELECT * FROM manufacturer")
    fun getManufacturers(): Flow<List<ManufacturerEntity>>

    @Query("SELECT * FROM manufacturer WHERE id = :id")
    fun getManufacturerById(id: Long): Flow<ManufacturerEntity>

    @Query("DELETE FROM manufacturer")
    suspend fun clearAllManufacturers()
}