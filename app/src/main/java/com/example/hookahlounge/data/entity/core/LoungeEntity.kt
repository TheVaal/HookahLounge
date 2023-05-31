package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "lounge",
)
data class LoungeEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val address: String,
)