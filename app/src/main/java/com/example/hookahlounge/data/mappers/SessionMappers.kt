package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.SessionDto
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.projection.SessionWithFields
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

fun SessionWithFields.toSession(): Session {
    return Session(
        id = session.id,
        loungeId = session.loungeId,
        status = session.status,
        accessCode = session.accessCode,
        ownerId = session.ownerId.toString(),
        ownerCountryCode = session.ownerCountryCode.toString(),
        ownerName = session.ownerName,
        bookingDate = session.bookingDate,
        lounge = lounge.toLounge()
    )
}

fun Session.toDto(): SessionDto {
    return SessionDto(
        id = id,
        loungeId = loungeId,
        status = status,
        accessCode = accessCode,
        ownerId = if (ownerId != "") ownerId.toLong() else 0L,
        ownerCountryCode = if (ownerCountryCode != "") ownerCountryCode.toLong() else 0L,
        ownerName = ownerName,
        bookingDate = bookingDate,
    )
}

