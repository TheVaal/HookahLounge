package com.example.hookahlounge.data.dto.datasource

data class OrderInternal(
    val id: Long,
    val loungeId: Long,
    val tableId: Long,
    val sessionID: Long,
    val sum: Double,
)