package com.example.hookahlounge.data.dto.datasource

data class TableDto(
    val id: Long,
    val name: String,
    val seats: Int,
    val loungeId: Long,
)