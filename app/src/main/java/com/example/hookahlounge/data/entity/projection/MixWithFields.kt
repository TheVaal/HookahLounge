package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.MixEntity

data class MixWithFields(
    @Embedded val mix: MixEntity,
    @Relation(parentColumn = "id", entityColumn = "mixId", entity = HookahEntity::class)
    val hookah: List<HookahEntityWithFields> = listOf()
)
