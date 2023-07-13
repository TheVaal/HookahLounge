package com.example.hookahlounge.data.dto.datasource

data class TobaccoDto(
    val id: Long = 0L,
    val manufacturerId: Long = 0L,
    val taste: String = "",
    val hardnessId: Long = 0L,
    val hardness: HardnessDto = HardnessDto(),
    val manufacturer: ManufacturerDto = ManufacturerDto(),
)