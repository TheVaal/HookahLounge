package com.example.hookahlounge.data.dto.datasource

data class LoungeMenuDto(
    val id: Long,
    val menuId: Long,
    val menu: MenuDto = MenuDto(),
    val loungeId: Long,
    val price: Double,
)