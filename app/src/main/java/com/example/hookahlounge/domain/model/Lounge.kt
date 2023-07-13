package com.example.hookahlounge.domain.model

data class Lounge(
    val id: Long = 0L,
    val name: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val country: String = "",
    val tables: List<Table> = listOf(),
    val menu: List<LoungeMenu> = listOf(),
    val tobacco: List<LoungeTobacco> = listOf()
)