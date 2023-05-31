package com.example.hookahlounge.data.entity.projection

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.data.entity.core.TobaccoEntity

data class TobaccoByManufacturer(
    @Embedded val tobacco: TobaccoEntity,
    @Relation(parentColumn = "manufacturerId", entityColumn = "id")
    val manufacturer: ManufacturerEntity,
    @Relation(parentColumn = "hardnessId", entityColumn = "id")
    val hardness: HardnessEntity,
)

