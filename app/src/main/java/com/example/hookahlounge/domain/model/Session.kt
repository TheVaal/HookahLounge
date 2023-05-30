package com.example.hookahlounge.domain.model

data class Session(
    val id: Long = 0L,
    val accessCode: String = "",
    val owner: User = User(),
    val lounge: Lounge = Lounge(),
    val status: Boolean = false,
    val lockDate: String = "",
)

