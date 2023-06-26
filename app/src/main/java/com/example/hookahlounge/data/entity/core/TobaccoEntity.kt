package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tobacco"
)
data class TobaccoEntity(
    @PrimaryKey val id: Long,
    val manufacturerId: Long,
    val taste: String,
    val hardnessId: Long,
)