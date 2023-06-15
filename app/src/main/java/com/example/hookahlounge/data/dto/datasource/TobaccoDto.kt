package com.example.hookahlounge.data.dto.datasource

data class TobaccoDto(
    val id: Long,
    val manufacturerId: Long,
    val taste: String,
    val hardnessId: Long,
    val hardness: HardnessDto,
    val manufacturer: ManufacturerDto,
)