package com.example.hookahlounge.data.dto.datasource

data class WaiterCallInternal(
    val id: Long,
    val sessionId: Long,
    val answered: Boolean = false,
)