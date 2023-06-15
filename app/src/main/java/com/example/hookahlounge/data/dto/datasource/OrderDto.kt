package com.example.hookahlounge.data.dto.datasource

data class OrderDto(
    val id: Long,
    val loungeId: Long,
    val tableId: Long,
    val sessionId: Long,
    val sum: Double,
    val closed: Int,
    val status: String,
)