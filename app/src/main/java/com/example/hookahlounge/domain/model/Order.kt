package com.example.hookahlounge.domain.model

data class Order(
    val id: Long,
    val table: Table,
    val session: Session,
    val sum: Double,
    val closed: Boolean = false,
)