package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tobacco",
    foreignKeys = [
        ForeignKey(
            entity = ManufacturerEntity::class,
            parentColumns = ["id"],
            childColumns = ["manufacturerId"]
        ),
        ForeignKey(
            entity = ManufacturerEntity::class,
            parentColumns = ["id"],
            childColumns = ["hardnessId"]
        ),
    ]
)
data class TobaccoEntity(
    @PrimaryKey val id: Long,
    val manufacturerId: Long,
    val taste: String,
    val hardnessId: Long,
)