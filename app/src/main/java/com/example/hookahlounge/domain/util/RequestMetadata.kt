package com.example.hookahlounge.domain.util

import com.squareup.moshi.Json

data class RequestMetadata(

    @Json(name = "current_page")
    val currentPage: Int?,
    @Json(name = "from")
    val previousPage: Int?,
    @Json(name = "last_page")
    val lastPage: Int?
)
