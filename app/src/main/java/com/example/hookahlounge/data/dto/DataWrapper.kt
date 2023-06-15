package com.example.hookahlounge.data.dto

import com.example.hookahlounge.domain.util.RequestMetadata
import com.squareup.moshi.Json

data class DataWrapper<T>(
    @Json(name = "data")
    val data: T,

    @Json(name = "meta")
    val meta: RequestMetadata = RequestMetadata(1,0,1)
)