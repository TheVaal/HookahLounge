package com.example.hookahlounge.data.dto.datasource

data class LoungeTobaccoDto(
    val id: Long = 0L,
    val tobaccoId: Long = 0L,
    val tobacco: TobaccoDto = TobaccoDto(),
    val loungeId: Long = 0L,
    val price: Double = 0.0,
)
