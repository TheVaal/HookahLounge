package com.example.hookahlounge.presentation.tobacco.viewmodel.list

import com.example.hookahlounge.domain.model.LoungeTobacco
import com.example.hookahlounge.domain.model.Tobacco

data class LoungeTobaccoListUiState(
    val isLoading: Boolean = false,
    val loungeTobaccoList: List<LoungeTobacco> = listOf(),
    val tobaccoList: List<Tobacco> = listOf(),
) {
    val availableToAdd: List<Tobacco> = tobaccoList.filter { tobacco ->
        loungeTobaccoList.none { it.tobaccoId == tobacco.id }
    }
}

