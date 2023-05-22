package com.example.hookahlounge.domain.model

data class InOrder(
    val id: Long,
    val orderId: Long,
    val guest: Int?,
    val menuId: Long,
    val quantity: Double,
)