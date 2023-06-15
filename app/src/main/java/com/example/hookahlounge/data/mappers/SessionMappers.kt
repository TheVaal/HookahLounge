package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.SessionDto
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.domain.model.Session

fun SessionDto.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = id,
        loungeId = loungeId,
        status = status,
        accessCode = accessCode,
        ownerId = ownerId,
        bookingDate = bookingDate,
    )
}

fun SessionEntity.toSession(): Session {
    return Session(
        id = id,
        loungeId = loungeId,
        status = status,
        accessCode = accessCode,
        ownerId = ownerId,
        bookingDate = bookingDate,
    )
}

