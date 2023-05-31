package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "loungeTobacco",
    foreignKeys = [
        ForeignKey(
            entity = TobaccoEntity::class,
            parentColumns = ["id"],
            childColumns = ["tobaccoId"]
        ),
    ]
)
data class LoungeTobaccoEntity(
    @PrimaryKey val id: Long,
    val tobaccoId: Long,
    val loungeId: Long,
    val price: Double,
)