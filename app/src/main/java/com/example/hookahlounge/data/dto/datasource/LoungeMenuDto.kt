package com.example.hookahlounge.data.dto.datasource

data class LoungeMenuDto(
    val id: Long = 0L,
    val menuId: Long = 0L,
    val menu: MenuDto = MenuDto(),
    val loungeId: Long = 0L,
    val price: Double = 0.0,
)