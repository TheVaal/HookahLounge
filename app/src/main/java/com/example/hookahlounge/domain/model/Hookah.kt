package com.example.hookahlounge.domain.model

data class Hookah(
    val id: Long = 0L,
    val mixId: Long = 0L,
    val weight: String = "",
    val loungeTobaccoId: Long = 0L,
    val loungeTobacco: LoungeTobacco = LoungeTobacco(),
)