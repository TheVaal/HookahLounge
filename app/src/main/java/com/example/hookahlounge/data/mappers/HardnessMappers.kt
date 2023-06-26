package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.HardnessDto
import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.domain.model.Hardness


fun HardnessDto.toEntity(): HardnessEntity {
    return HardnessEntity(
        id = id,
        name = name
    )
}

fun HardnessEntity.toModel(): Hardness {
    return Hardness(
        id = id,
        name = name
    )
}

fun Hardness.toDto(): HardnessDto {
    return HardnessDto(
        id = id,
        name = name,
    )
}