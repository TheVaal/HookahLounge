package com.example.hookahlounge.data.dto.datasource

data class WaiterCallDto(
    val id: Long,
    val sessionId: Long,
    val answered: Boolean = false,
)