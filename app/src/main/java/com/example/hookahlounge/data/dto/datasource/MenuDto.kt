package com.example.hookahlounge.data.dto.datasource

import com.squareup.moshi.Json

data class MenuDto(
    val id: Long = 0L,
    @Json(name = "name")
    val name: String = "",
)