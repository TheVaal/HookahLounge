package com.example.hookahlounge.data.dto.datasource

data class SessionDto(
    val id: Long,
    val accessCode: String,
    val ownerId: Long,
    val ownerCountryCode: Long = 0L,
    val ownerName: String = "",
    val loungeId: Long,
    val status: String,
    val bookingDate: String,
)

