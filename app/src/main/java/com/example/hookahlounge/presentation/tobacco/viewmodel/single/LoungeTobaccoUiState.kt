package com.example.hookahlounge.presentation.tobacco.viewmodel.single

import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.model.Manufacturer

data class LoungeTobaccoUiState(
    val isLoading: Boolean = false,
    val loungeTobacco: LoungeTobacco = LoungeTobacco(),
    val manufacturers: List<Manufacturer> = listOf(),
    val hardnessList: List<Hardness> = listOf()
)