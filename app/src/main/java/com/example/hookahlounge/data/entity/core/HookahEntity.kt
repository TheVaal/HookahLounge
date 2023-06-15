package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("hookah",
    foreignKeys = [
        ForeignKey(
            entity = LoungeTobaccoEntity::class,
            parentColumns = ["id"],
            childColumns = ["loungeTobaccoId"]
        ),
        ForeignKey(
            entity = MixEntity::class,
            parentColumns = ["id"],
            childColumns = ["mixId"]
        ),
    ]
)
data class HookahEntity(
    @PrimaryKey val id: Long,
    val mixId: Long,
    val weight: Double,
    val loungeTobaccoId: Long,
)