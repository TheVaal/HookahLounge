package com.example.hookahlounge.data.entity.core

data class WaiterCallEntity(
    val id: Long,
    val sessionId: Long,
    val answered: Boolean = false,
)