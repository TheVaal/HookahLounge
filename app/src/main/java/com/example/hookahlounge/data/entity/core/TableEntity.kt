package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loungeTable")
data class TableEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val seats: Int,
    val loungeId: Long,
)