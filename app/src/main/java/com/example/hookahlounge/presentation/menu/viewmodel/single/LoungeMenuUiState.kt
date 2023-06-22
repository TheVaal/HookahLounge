package com.example.hookahlounge.presentation.menu.viewmodel.single

import com.example.hookahlounge.domain.model.LoungeMenu

data class LoungeMenuUiState(
    val isLoading: Boolean = false,
    val loungeMenu: LoungeMenu = LoungeMenu()
)
