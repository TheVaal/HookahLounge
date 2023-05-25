package com.example.hookahlounge.domain.model

data class Table(
    val id: Long,
    val name: String,
    val seats: Int,
    val loungeId: Long,
)