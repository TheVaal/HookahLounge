package com.example.hookahlounge.domain.model

data class InOrder(
    val id: Long = 0L,
    val orderId: Long,
    val guest: Int = 1,
    val menuId: Long = 0L,
    val menu: LoungeMenu = LoungeMenu(),
    val quantity: String,
)