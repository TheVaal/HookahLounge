package com.example.hookahlounge.data.entity.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Upsert
    suspend fun upsertAll(order: List<OrderEntity>)

    @Upsert
    suspend fun upsertOrder(order: OrderEntity)

    @Query("SELECT * FROM 'order' WHERE id = :id")
    fun getOrder(id: Long): Flow<OrderWithFields>

    @Query("SELECT * FROM 'order'")
    fun pagingSource(): PagingSource<Int, OrderWithFields>

    @Query("DELETE FROM 'order'")
    suspend fun clearAll()

}