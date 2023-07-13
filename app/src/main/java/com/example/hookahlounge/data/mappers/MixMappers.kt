package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.dto.datasource.HookahDto
import com.example.hookahlounge.data.dto.datasource.MixDto
import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.MixEntity
import com.example.hookahlounge.data.entity.projection.HookahEntityWithFields
import com.example.hookahlounge.data.entity.projection.MixWithFields
import com.example.hookahlounge.domain.model.Hookah
import com.example.hookahlounge.domain.model.Mix

fun MixDto.toEntityWithFields(): MixWithFields {
    return MixWithFields(
        mix = toEntity(),
        hookah = hookahs.map {
            it.toEntityWithFields()
        }
    )
}

fun MixDto.toEntity(): MixEntity {
    return MixEntity(
        id = id,
        orderId = orderId,
        weight = weight
    )
}

fun HookahDto.toEntity(): HookahEntity {
    return HookahEntity(
        id = id,
        mixId = mixId,
        weight = weight,
        loungeTobaccoId = loungeTobaccoId,
    )
}


fun HookahDto.toEntityWithFields(): HookahEntityWithFields {
    return HookahEntityWithFields(
        hookah = toEntity(),
        loungeTobacco = loungeTobacco.toEntityWithFields()
    )
}

fun MixWithFields.toModel(): Mix {
    return Mix(
        id = mix.id,
        orderId = mix.orderId,
        weight = mix.weight.toString(),
        hookah = hookah.map { it.toModel() }
    )
}


fun HookahEntityWithFields.toModel(): Hookah {
    return Hookah(
        id = hookah.id,
        weight = hookah.weight.toString(),
        loungeTobacco = loungeTobacco.toModel(),
        loungeTobaccoId = hookah.loungeTobaccoId
    )
}

fun Mix.toDto(): MixDto {
    return MixDto(
        id = id,
        orderId = orderId,
        weight = weight.toDouble()
    )
}

fun Hookah.toDto(): HookahDto {
    return HookahDto(
        id = id,
        weight = weight.toDouble(),
        loungeTobaccoId = loungeTobaccoId,
        mixId = mixId
    )
}