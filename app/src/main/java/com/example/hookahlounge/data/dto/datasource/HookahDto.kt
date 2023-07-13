package com.example.hookahlounge.data.dto.datasource

data class HookahDto(
    val id: Long,
    val mixId: Long,
    val loungeTobaccoId: Long,
    val weight: Double,
    val loungeTobacco: LoungeTobaccoDto = LoungeTobaccoDto()
)