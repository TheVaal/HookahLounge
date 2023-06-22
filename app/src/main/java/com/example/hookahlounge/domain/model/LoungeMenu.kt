package com.example.hookahlounge.domain.model

data class LoungeMenu(
    val id: Long = 0L,
    val menuId: Long = 0L,
    val menu: Menu = Menu(),
    val loungeId: Long = 0L,
    val price: String = "",
)