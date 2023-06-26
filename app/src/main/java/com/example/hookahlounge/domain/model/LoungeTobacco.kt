package com.example.hookahlounge.domain.model

data class LoungeTobacco(
    val id: Long = 0L,
    val tobaccoId: Long = 0L,
    val tobacco: Tobacco = Tobacco(),
    val loungeId: Long = 0L,
    val price: String = "",
)
