package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.OrderDto
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.data.entity.projection.SessionWithFields
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

fun OrderWithFields.toOrder(): Order {
    return Order(
        id = order.id,
        tableId = order.tableId,
        sessionId = order.sessionId,
        sum = order.sum,
        closed = order.closed,
        status = order.status,
        session = session.toSession(),
        table = table.toTable()
    )
}

fun OrderDto.toOrderWithFields(): OrderWithFields {
    return OrderWithFields(
        order = toOrderEntity(),
        table = table.toTableEntity(),
        session = SessionWithFields(
            session = session.toSessionEntity(),
            lounge = lounge.toLoungeEntity()
        )
    )
}

fun Order.toDto(): OrderDto{
    return OrderDto(
        id = id,
        sessionId = sessionId,
        tableId = tableId,
        loungeId = table.loungeId,
        sum = sum,
        closed = if (closed) 1 else 0,
        status = status,
    )
}