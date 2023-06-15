package com.example.hookahlounge.domain.model

data class Order(
    val id: Long = 0L,
    val tableId: Long = 0L,
    val sessionId: Long = 0L,
    val table: Table? = null,
    val session: Session? = null,
    val sum: Double = 0.0,
    val closed: Boolean = false,
    val status: String = "O",
)