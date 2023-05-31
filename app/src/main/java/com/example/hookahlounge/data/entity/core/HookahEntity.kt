package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hookah")
data class HookahEntity(
    @PrimaryKey val id: Long,
    val orderId: Long,
    val mixId: Long,
    val weight: Double,
)