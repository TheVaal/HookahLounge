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
        ownerCountryCode = ownerCountryCode,
        ownerName = ownerName,
        bookingDate = bookingDate,
    )
}

fun SessionEntity.toSession(): Session {
    return Session(
        id = id,
        loungeId = loungeId,
        status = status,
        accessCode = accessCode,
        ownerId = ownerId.toString(),
        ownerCountryCode = ownerCountryCode.toString(),
        ownerName = ownerName,
        bookingDate = bookingDate,
    )
}

fun Session.toDto(): SessionDto {
    return SessionDto(
        id = id,
        loungeId = loungeId,
        status = status,
        accessCode = accessCode,
        ownerId = ownerId.toLong(),
        ownerCountryCode = ownerCountryCode.toLong(),
        ownerName = ownerName,
        bookingDate = bookingDate,
    )
}

