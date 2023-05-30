package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.util.BaseViewModel
import javax.inject.Inject

class LoungeViewModel  @Inject constructor(): BaseViewModel<LoungeUiState>() {
    override val initialState: LoungeUiState
        get() = LoungeUiState()

}

data class LoungeUiState(
    val list: Lounge = Lounge(),
    val isLoading: Boolean = false,
)