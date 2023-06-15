package com.example.hookahlounge.data.dto.datasource

data class LoungeDto(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
    val tables: List<TableDto> = listOf()
)