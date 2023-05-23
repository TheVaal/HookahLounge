package com.example.hookahlounge.domain.model

data class Order(
    val id: Long,
    val loungeId: Long,
    val tableId: Long,
    val sessionID: Long,
    val sum: Double,
    val closed: Boolean,
)