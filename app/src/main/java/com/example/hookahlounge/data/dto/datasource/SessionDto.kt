package com.example.hookahlounge.data.dto.datasource

data class SessionDto(
    val id: Long,
    val accessCode: String,
    val ownerId: Long,
    val loungeId: Long,
    val status: String,
    val bookingDate: String,
)

