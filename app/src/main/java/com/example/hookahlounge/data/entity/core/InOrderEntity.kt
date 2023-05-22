package com.example.hookahlounge.data.entity.core

data class InOrderEntity(
    val id: Long,
    val orderId: Long,
    val guest: Int?,
    val menuId: Long,
    val quantity: Double,
)