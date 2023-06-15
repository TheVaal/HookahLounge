package com.example.hookahlounge.domain.model

data class Table(
    val id: Long = 0L,
    val name: String = "",
    val seats: String = "",
    val loungeId: Long = 0L,
)