package com.example.hookahlounge.data.entity.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.projection.SessionWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Upsert
    suspend fun upsertAll(sessions: List<SessionEntity>)

    @Upsert
    suspend fun upsert(session: SessionEntity)

    @Query("SELECT * FROM 'session'")
    fun pagingSource(): PagingSource<Int, SessionEntity>

    @Query("SELECT * FROM 'session'")
    fun getSessions(): Flow<List<SessionWithFields>>

    @Query("SELECT * FROM 'session' WHERE id = :id")
    fun getSessionById(id: Long): Flow<SessionEntity>

    @Query("DELETE FROM 'session'")
    suspend fun clearAll()
}