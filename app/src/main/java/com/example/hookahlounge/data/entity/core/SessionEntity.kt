package com.example.hookahlounge.data.entity.core

data class SessionEntity(
    val id: Long,
    val accessCode: String,
    val ownerId: Long,
    val loungeId: Long,
    val status: Boolean,
    val lockDate: String,
)

