package com.example.hookahlounge.data.entity.core

data class OrderEntity(
    val id: Long,
    val loungeId: Long,
    val tableId: Long,
    val sessionID: Long,
    val sum: Double,
)