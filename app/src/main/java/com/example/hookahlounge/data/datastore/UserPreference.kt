package com.example.hookahlounge.data.datastore

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPreference(
    @SerialName("login") val login: String = "",
    @SerialName("authToken") val token: String = "",
    @SerialName("workplace_id") val workplaceId: Long = 0L
)
