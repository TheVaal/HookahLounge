package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.projection.InOrderWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface InOrderDao {
    @Upsert
    suspend fun upsertAll(inOrder: List<InOrderEntity>)

    @Upsert
    suspend fun upsertInOrder(inOrder: InOrderEntity)

    @Query("SELECT * FROM 'inOrder' WHERE orderId = :orderId")
    fun getInOrder(orderId: Long): Flow<List<InOrderWithFields>>

    @Query("DELETE FROM 'inOrder'")
    suspend fun clearAll()

}