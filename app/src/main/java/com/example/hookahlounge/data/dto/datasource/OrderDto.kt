package com.example.hookahlounge.data.dto.datasource

data class OrderDto(
    val id: Long,
    val loungeId: Long,
    val lounge: LoungeDto = LoungeDto(),
    val tableId: Long,
    val table: TableDto = TableDto(),
    val sessionId: Long,
    val session: SessionDto = SessionDto(),
    val sum: Double,
    val closed: Int,
    val status: String,
)