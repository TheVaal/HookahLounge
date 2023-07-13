package com.example.hookahlounge.domain.model

import com.example.hookahlounge.presentation.order.viewmodel.OrderStatus

data class Order(
    val id: Long = 0L,
    val tableId: Long = 0L,
    val sessionId: Long = 0L,
    val table: Table = Table(),
    val session: Session = Session(),
    val sum: Double = 0.0,
    val closed: Boolean = false,
    val status: String = OrderStatus.BOOKED.value,
)