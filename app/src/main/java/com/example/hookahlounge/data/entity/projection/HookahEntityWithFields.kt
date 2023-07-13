package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity

data class HookahEntityWithFields(
    @Embedded val hookah: HookahEntity,
    @Relation(parentColumn = "loungeTobaccoId", entityColumn = "id", entity = LoungeTobaccoEntity::class)
    val loungeTobacco: LoungeTobaccoWithFields,
)