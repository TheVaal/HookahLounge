package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.ManufacturerDto
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.domain.model.Manufacturer

fun ManufacturerDto.toEntity(): ManufacturerEntity {
    return ManufacturerEntity(
        id = id,
        name = name
    )
}

fun ManufacturerEntity.toModel(): Manufacturer {
    return Manufacturer(
        id = id,
        name = name
    )
}

fun Manufacturer.toDto(): ManufacturerDto {
    return ManufacturerDto(
        id = id,
        name = name,
    )
}