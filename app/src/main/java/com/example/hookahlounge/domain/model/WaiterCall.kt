package com.example.hookahlounge.domain.model

data class WaiterCall(
    val id: Long,
    val sessionId: Long,
    val answered: Boolean = false,
)