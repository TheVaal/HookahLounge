package com.example.hookahlounge.domain.model

data class Session(
    val id: Long = 0L,
    val ownerId: Long = 0L,
    val loungeId: Long = 0L,
    val accessCode: String = "",
    val owner: User = User(),
    val lounge: Lounge = Lounge(),
    val status: String = "BD",
    val bookingDate: String = "",
)

