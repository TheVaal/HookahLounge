package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.MixEntity
import com.example.hookahlounge.data.entity.projection.HookahEntityWithFields
import com.example.hookahlounge.data.entity.projection.MixWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface HookahDao {
    @Upsert
    suspend fun upsertAllHookah(hookahs: List<HookahEntity>)

    @Upsert
    suspend fun upsertHookah(hookah: HookahEntity)

    @Query("SELECT * FROM hookah  WHERE mixId = :mixId")
    fun getHookahByMix(mixId: Long): Flow<List<HookahEntityWithFields>>

    @Query("SELECT * FROM hookah WHERE id = :id")
    fun getHookahById(id: Long): Flow<HookahEntityWithFields>

    @Query("DELETE FROM hookah")
    suspend fun clearAllHookah()

    @Upsert
    suspend fun upsertAllMixes(mixes: List<MixEntity>)

    @Upsert
    suspend fun upsertMix(mix: MixEntity)

    @Query("SELECT * FROM hookahMix  WHERE orderId = :orderId")
    fun getMixesByOrder(orderId: Long): Flow<List<MixWithFields>>

    @Query("SELECT * FROM hookahMix WHERE id = :id")
    fun getMixById(id: Long): Flow<MixWithFields>

    @Query("DELETE FROM hookahMix")
    suspend fun clearAllMixes()
}