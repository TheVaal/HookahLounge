package com.example.hookahlounge.data.dto.datasource

data class LoungeTobaccoDto(
    val id: Long,
    val tobaccoId: Long,
    val tobacco: TobaccoDto,
    val loungeId: Long,
    val price: Double,
)
