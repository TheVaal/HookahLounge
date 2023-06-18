package com.example.hookahlounge.data.entity.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.OrderEntity

@Dao
interface OrderDao {
    @Upsert
    suspend fun upsertAll(order: List<OrderEntity>)

    @Query("SELECT * FROM 'order'")
    fun pagingSource(): PagingSource<Int, OrderEntity>

    @Query("DELETE FROM 'order'")
    suspend fun clearAll()

}