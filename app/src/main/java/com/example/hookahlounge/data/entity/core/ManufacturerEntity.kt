package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manufacturer")
data class ManufacturerEntity(
    @PrimaryKey val id: Long,
    val name: String,
)