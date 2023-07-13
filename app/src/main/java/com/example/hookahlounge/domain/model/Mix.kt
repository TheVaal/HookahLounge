package com.example.hookahlounge.domain.model

data class Mix(
    val id: Long = 0L,
    val orderId: Long = 0L,
    val weight: String = "",
    val hookah: List<Hookah> = listOf()
)