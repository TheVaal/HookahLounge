package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.LoungeTobaccoDto
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.projection.LoungeTobaccoWithFields
import com.example.hookahlounge.domain.model.LoungeTobacco

fun LoungeTobaccoDto.toEntity(): LoungeTobaccoEntity {
    return LoungeTobaccoEntity(
        id = id,
        loungeId = loungeId,
        price = price,
        tobaccoId = tobaccoId
    )
}

fun LoungeTobaccoDto.toEntityWithFields(): LoungeTobaccoWithFields {
    return LoungeTobaccoWithFields(
        loungeTobacco = toEntity(),
        tobacco = tobacco.toEntityWithFields()
    )
}

fun LoungeTobaccoWithFields.toModel(): LoungeTobacco {
    return LoungeTobacco(
        id = loungeTobacco.id,
        price = loungeTobacco.price.toString(),
        loungeId = loungeTobacco.loungeId,
        tobaccoId = loungeTobacco.tobaccoId,
        tobacco = tobacco.toModel(),
    )
}

fun LoungeTobacco.toDto(): LoungeTobaccoDto {
    return LoungeTobaccoDto(
        id = id,
        loungeId = loungeId,
        tobaccoId = tobaccoId,
        price = price.toDouble(),
        tobacco = tobacco.toDto()
    )
}