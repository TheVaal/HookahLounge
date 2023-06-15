package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.OrderDto
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.domain.model.Order

fun OrderDto.toOrderEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        loungeId = loungeId,
        tableId = tableId,
        sessionId = sessionId,
        sum = sum,
        closed = closed != 0,
        status = status
    )
}

fun OrderEntity.toOrder(): Order {
    return Order(
        id = id,
        tableId = tableId,
        sessionId = sessionId,
        sum = sum,
        closed = closed,
        status = status
    )
}