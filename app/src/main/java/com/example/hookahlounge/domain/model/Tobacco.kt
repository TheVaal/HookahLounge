package com.example.hookahlounge.domain.model

data class Tobacco(
    val id: Long = 0L,
    val manufacturerId: Long = 0L,
    val manufacturer: Manufacturer = Manufacturer(),
    val taste: String = "",
    val hardnessId: Long = 0L,
    val hardness: Hardness = Hardness()
)