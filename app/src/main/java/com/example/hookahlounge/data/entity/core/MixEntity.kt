package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "hookahMix",

)
data class MixEntity(
    @PrimaryKey val id: Long,
    val orderId: Long,
    val weight: Double,
)