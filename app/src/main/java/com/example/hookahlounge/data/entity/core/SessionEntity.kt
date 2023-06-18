package com.example.hookahlounge.data.entity.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class SessionEntity(
    @PrimaryKey val id: Long,
    val accessCode: String,
    val ownerId: Long,
    val ownerCountryCode: Long = 0L,
    val ownerName: String,
    val loungeId: Long,
    val status: String,
    val bookingDate: String,
)

