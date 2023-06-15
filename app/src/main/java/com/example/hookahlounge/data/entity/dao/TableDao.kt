package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.TableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {
    @Upsert
    suspend fun upsertAll(table: List<TableEntity>)

    @Upsert
    suspend fun upsert(table: TableEntity)

    @Query("SELECT * FROM loungeTable")
    fun getTables(): Flow<List<TableEntity>>

    @Query("SELECT * FROM loungeTable WHERE id = :id")
    fun getTable(id: Long): Flow<TableEntity>

    @Query("DELETE FROM loungeTable")
    suspend fun clearAll()

}
