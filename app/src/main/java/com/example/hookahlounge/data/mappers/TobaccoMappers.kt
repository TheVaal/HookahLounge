package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.TobaccoDto
import com.example.hookahlounge.data.entity.core.TobaccoEntity
import com.example.hookahlounge.data.entity.projection.TobaccoWithFields
import com.example.hookahlounge.domain.model.Tobacco


fun TobaccoDto.toEntity(): TobaccoEntity {
    return TobaccoEntity(
        id = id,
        manufacturerId = manufacturerId,
        taste = taste,
        hardnessId = hardnessId
    )
}

fun TobaccoEntity.toModel(): Tobacco {
    return Tobacco(
        id = id,
        manufacturerId = manufacturerId,
        taste = taste,
        hardnessId = hardnessId
    )
}

fun Tobacco.toDto(): TobaccoDto {
    return TobaccoDto(
        id = id,
        manufacturerId = manufacturerId,
        taste = taste,
        hardnessId = hardnessId,
        manufacturer = manufacturer.toDto(),
        hardness = hardness.toDto()
    )
}

fun TobaccoDto.toEntityWithFields(): TobaccoWithFields {
    return TobaccoWithFields(
        tobacco = toEntity(),
        manufacturer = manufacturer.toEntity(),
        hardness = hardness.toEntity()
    )
}

fun TobaccoWithFields.toModel(): Tobacco {
    return Tobacco(
        id = tobacco.id,
        manufacturer = manufacturer.toModel(),
        hardness = hardness.toModel(),
        hardnessId = tobacco.hardnessId,
        manufacturerId = tobacco.manufacturerId,
        taste = tobacco.taste
    )
}