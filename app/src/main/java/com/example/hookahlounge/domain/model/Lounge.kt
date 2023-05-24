package com.example.hookahlounge.domain.model

data class Lounge(
    val id: Long,
    val name: String,
    val address: String,
    val tables: List<Table> = listOf(),
)