package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.HookahEntity

data class HookahEntityWithFields(
    @Embedded val hookah: HookahEntity,
    @Relation(parentColumn = "loungeTobaccoId", entityColumn = "id")
    val loungeTobacco: LoungeTobaccoWithFields,
)