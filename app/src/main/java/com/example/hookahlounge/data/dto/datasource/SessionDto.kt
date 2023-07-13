package com.example.hookahlounge.data.dto.datasource

data class SessionDto(
    val id: Long = 0L,
    val accessCode: String = "",
    val ownerId: Long = 0L,
    val ownerCountryCode: Long = 0L,
    val ownerName: String = "",
    val loungeId: Long = 0L,
    val status: String = "",
    val bookingDate: String = "",
)

