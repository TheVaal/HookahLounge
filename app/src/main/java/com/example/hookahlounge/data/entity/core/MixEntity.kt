package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "hookahMix",
    foreignKeys = [
        ForeignKey(
            entity = LoungeTobaccoEntity::class,
            parentColumns = ["id"],
            childColumns = ["loungeTobaccoId"]
        ),
    ]
)
data class MixEntity(
    @PrimaryKey val id: Long,
    val orderId: Long,
    val loungeTobaccoId: Long,
    val weight: Double,
)