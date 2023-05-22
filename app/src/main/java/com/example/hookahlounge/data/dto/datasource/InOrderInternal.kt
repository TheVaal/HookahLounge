package com.example.hookahlounge.data.dto.datasource

data class InOrderInternal(
    val id: Long,
    val orderId: Long,
    val guest: Int?,
    val menuId: Long,
    val quantity: Double,
)