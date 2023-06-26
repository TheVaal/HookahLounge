package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.HardnessEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HardnessDao {
    @Upsert
    suspend fun upsertAllHardness(loungeHardness: List<HardnessEntity>)

    @Upsert
    suspend fun upsertHardness(hardness: HardnessEntity)

    @Query("SELECT * FROM hardness")
    fun getHardness(): Flow<List<HardnessEntity>>

    @Query("SELECT * FROM hardness WHERE id = :id")
    fun getHardnessById(id: Long): Flow<HardnessEntity>

    @Query("DELETE FROM hardness")
    suspend fun clearAllHardness()
}