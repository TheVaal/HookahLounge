package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.data.entity.core.TobaccoEntity
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface TobaccoDao {
    @Upsert
    suspend fun upsertAllLoungeTobaccos(loungeMenus: List<LoungeTobaccoEntity>)

    @Upsert
    suspend fun upsertLoungeTobacco(loungeTobacco: LoungeTobaccoEntity)

    @Query("SELECT * FROM loungeTobacco")
    fun getLoungeTobacco(): Flow<List<LoungeTobaccoWithFields>>

    @Query("SELECT * FROM loungeTobacco WHERE id = :id")
    fun getLoungeTobaccoById(id: Long): Flow<LoungeTobaccoWithFields>

    @Query("SELECT * FROM loungeTobacco WHERE loungeId = :loungeId")
    fun getLoungeTobaccoByLounge(loungeId: Long): Flow<List<LoungeTobaccoWithFields>>

    @Query("DELETE FROM loungeTobacco")
    suspend fun clearAllLoungeTobaccos()

    @Upsert
    suspend fun upsertAllTobacco(tobaccos: List<TobaccoEntity>)

    @Upsert
    suspend fun upsertTobacco(tobacco: TobaccoEntity)

    @Query("SELECT * FROM tobacco")
    fun getTobacco(): Flow<List<TobaccoWithFields>>

    @Query("SELECT * FROM tobacco WHERE id = :id")
    fun getTobaccoById(id: Long): Flow<TobaccoWithFields>

    @Query("DELETE FROM tobacco")
    suspend fun clearAllTobaccos()
}