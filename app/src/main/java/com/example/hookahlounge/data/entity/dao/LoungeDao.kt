package com.example.hookahlounge.data.entity.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import kotlinx.coroutines.flow.Flow

@Dao
interface LoungeDao {
    @Upsert
    suspend fun upsertAll(lounges:List<LoungeEntity>)
    @Upsert
    suspend fun upsert(lounge: LoungeEntity)

    @Query("SELECT * FROM lounge")
    fun pagingSource():PagingSource<Int, LoungeEntity>

    @Query("SELECT * FROM lounge")
    fun getLounges():Flow<List<LoungeEntity>>

    @Query("SELECT * FROM lounge WHERE id = :id")
    fun getLoungeById(id: Long): Flow<LoungeWithTables>

    @Query("DELETE FROM lounge")
    suspend fun clearAll()
}