package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.TableDto
import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.domain.model.Table

fun TableDto.toTableEntity(): TableEntity {
    return TableEntity(
        id = id,
        name = name,
        seats = seats,
        loungeId = loungeId
    )
}

fun TableEntity.toTable(): Table {
    return Table(
        id = id,
        name = name,
        seats = seats.toString(),
        loungeId = loungeId,
    )
}

fun Table.toDto(): TableDto {
    return TableDto(
        id = id,
        name = name,
        seats = seats.toInt(),
        loungeId = loungeId,
    )
}