package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.core.ManufacturerEntity

data class LoungeTobaccoWithFields(
    @Embedded val loungeTobacco: LoungeTobaccoEntity,
    @Relation(parentColumn = "tobaccoId", entityColumn = "id")
    val tobacco: TobaccoByManufacturer,
)
