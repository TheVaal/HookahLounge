package com.example.hookahlounge.data.dto.datasource

import com.squareup.moshi.Json

data class InOrderDto(
    val id: Long,
    val orderId: Long,
    @Json(name = "guestNumber")
    val guest: Int,
    val loungeMenuId: Long,
    val loungeMenu: LoungeMenuDto = LoungeMenuDto(),
    val quantity: Double,
)