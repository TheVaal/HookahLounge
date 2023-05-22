package com.example.hookahlounge.domain.model

data class Session(
    val id: Long,
    val accessCode: String,
    val ownerId: Long,
    val loungeId: Long,
    val status: Boolean,
    val lockDate: String,
)

