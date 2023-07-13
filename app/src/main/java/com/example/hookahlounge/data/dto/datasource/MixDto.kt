package com.example.hookahlounge.data.dto.datasource

data class MixDto(
    val id: Long,
    val orderId: Long,
    val weight: Double,
    val hookahs: List<HookahDto> = listOf(),
)