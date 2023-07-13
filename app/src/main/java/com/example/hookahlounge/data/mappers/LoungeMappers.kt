package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.LoungeDto
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.domain.model.Lounge

fun LoungeDto.toLoungeEntity(): LoungeEntity{
    return LoungeEntity(
        id = id,
        name = name,
        address = address,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country)
}
fun LoungeDto.toLoungeWithTables(): LoungeWithTables{
    return LoungeWithTables(
        LoungeEntity(
        id = id,
        name = name,
        address = address,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country),
        tables.map { it.toTableEntity() },
        tobacco.map{it.toEntityWithFields()},
        menu.map { it.toEntityWithFields() }
    )
}

fun LoungeEntity.toLounge(): Lounge {
    return Lounge(
        id = id,
        name = name,
        address = address,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country)
}

fun Lounge.toEntity(): LoungeEntity {
    return LoungeEntity(
        id = id,
        name = name,
        address = address,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country)
}

fun Lounge.toDto(): LoungeDto {
    return LoungeDto(
        id = id,
        name = name,
        address = address,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country)
}

fun LoungeWithTables.toLounge(): Lounge {
    return Lounge(
        id = lounge.id,
        name = lounge.name,
        address = lounge.address,
        city = lounge.city,
        state = lounge.state,
        postalCode = lounge.postalCode,
        country = lounge.country,
        tables = tables.map { it.toTable() },
        menu = menu.map { it.toModel() },
        tobacco = tobacco.map { it.toModel() }
    )
}
