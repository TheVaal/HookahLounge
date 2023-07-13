package com.example.hookahlounge.data.dto.datasource

data class LoungeDto(
    val id: Long = 0L,
    val name: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val country: String = "",
    val tables: List<TableDto> = listOf(),
    val menu: List<LoungeMenuDto> = listOf(),
    val tobacco: List<LoungeTobaccoDto> = listOf()
)