package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "loungeTobacco",
)
data class LoungeTobaccoEntity(
    @PrimaryKey val id: Long,
    val tobaccoId: Long,
    val loungeId: Long,
    val price: Double,
)