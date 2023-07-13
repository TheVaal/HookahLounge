package com.example.hookahlounge.domain.model

data class User(
    val login: String = "",
    val password: String = "",
    val authToken: String = "",
    val currentWorkplace: Long = 0L
)
