package com.example.hookahlounge.data.entity.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.SessionEntity

@Dao
interface SessionDao {
    @Upsert
    suspend fun upsertAll(sessions: List<SessionEntity>)

    @Upsert
    suspend fun upsert(session: SessionEntity)

    @Query("SELECT * FROM 'session'")
    fun pagingSource(): PagingSource<Int, SessionEntity>

    @Query("DELETE FROM 'session'")
    suspend fun clearAll()
}