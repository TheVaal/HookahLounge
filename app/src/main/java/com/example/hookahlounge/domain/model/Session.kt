package com.example.hookahlounge.domain.model

data class Session(
    val id: Long = 0L,
    val ownerId: String = "",
    val ownerCountryCode: String = "380",
    val ownerName: String = "",
    val loungeId: Long = 0L,
    val accessCode: String = "",
    val lounge: Lounge = Lounge(),
    val status: String = "BD",
    val bookingDate: String = "",
)

