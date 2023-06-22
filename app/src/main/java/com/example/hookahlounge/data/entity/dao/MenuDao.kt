package com.example.hookahlounge.data.entity.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.data.entity.projection.LoungeMenuWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Upsert
    suspend fun upsertAllLoungeMenus(loungeMenus: List<LoungeMenuEntity>)

    @Upsert
    suspend fun upsertLoungeMenu(loungeMenu: LoungeMenuEntity)

    @Query("SELECT * FROM loungeMenu")
    fun getLoungeMenu(): Flow<List<LoungeMenuWithFields>>

    @Query("SELECT * FROM loungeMenu WHERE id = :id")
    fun getLoungeMenuById(id: Long): Flow<LoungeMenuWithFields>

    @Query("SELECT * FROM loungeMenu WHERE loungeId = :loungeId")
    fun getLoungeMenuByLounge(loungeId: Long): Flow<List<LoungeMenuWithFields>>

    @Query("DELETE FROM loungeMenu")
    suspend fun clearAllLoungeMenus()

    @Upsert
    suspend fun upsertAllMenus(loungeMenus: List<MenuEntity>)

    @Upsert
    suspend fun upsertMenu(loungeMenu: MenuEntity)

    @Query("SELECT * FROM menu")
    fun getMenu(): Flow<List<MenuEntity>>

    @Query("SELECT * FROM menu WHERE id = :id")
    fun getMenuById(id: Long): Flow<MenuEntity>

    @Query("DELETE FROM menu")
    suspend fun clearAllMenus()

}