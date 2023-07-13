package com.example.hookahlounge.data.dto.datasource

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "email") val login: String = "",
    val password: String = "",
    val token: String = "",
)
