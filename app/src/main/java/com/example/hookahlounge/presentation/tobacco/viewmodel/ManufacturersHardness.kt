package com.example.hookahlounge.presentation.tobacco.viewmodel

import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.model.Manufacturer

data class ManufacturersHardness(
    val manufacturersList: List<Manufacturer> = listOf(),
    val hardnessList: List<Hardness> = listOf(),
)