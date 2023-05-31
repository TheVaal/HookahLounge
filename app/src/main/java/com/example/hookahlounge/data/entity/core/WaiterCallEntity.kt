package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waiterCall")
data class WaiterCallEntity(
    @PrimaryKey val id: Long,
    val sessionId: Long,
    val tableId: Long,
    val answered: Boolean = false,
)