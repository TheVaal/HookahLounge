package com.example.hookahlounge.domain.model

data class Session(
    val id: Long,
    val accessCode: String,
    val owner: User,
    val lounge: Lounge,
    val status: Boolean,
    val lockDate: String,
)

