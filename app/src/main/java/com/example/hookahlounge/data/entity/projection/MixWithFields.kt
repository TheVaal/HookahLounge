package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.MixEntity

data class MixWithFields(
    @Embedded val mix: MixEntity,
    @Relation(parentColumn = "loungeTobaccoId", entityColumn = "id")
    val loungeTobacco: LoungeTobaccoWithFields
)
