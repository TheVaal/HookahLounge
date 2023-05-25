package com.example.hookahlounge.data.entity.core

data class TableEntity(
    val id: Long,
    val name: String,
    val seats: Int,
    val loungeId: Long,
)