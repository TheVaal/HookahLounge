package com.example.hookahlounge.domain.model

data class LoungeMenu(
    val id: Long,
    val menu: Menu,
    val lounge: Lounge,
    val price: Double,
)