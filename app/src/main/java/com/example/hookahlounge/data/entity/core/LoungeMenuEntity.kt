package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loungeMenu")
data class LoungeMenuEntity(
    @PrimaryKey val id: Long,
    val menuId: Long,
    val loungeId: Long,
    val price: Double,
)