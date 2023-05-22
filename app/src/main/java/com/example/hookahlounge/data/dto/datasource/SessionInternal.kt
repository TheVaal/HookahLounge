package com.example.hookahlounge.data.dto.datasource

data class SessionInternal(
    val id: Long,
    val accessCode: String,
    val ownerId: Long,
    val loungeId: Long,
    val status: Boolean,
    val lockDate: String,
)

