package com.example.hookahlounge.data.dto.datasource

data class InOrderDto(
    val id: Long,
    val orderId: Long,
    val guest: Int?,
    val menuId: Long,
    val quantity: Double,
)