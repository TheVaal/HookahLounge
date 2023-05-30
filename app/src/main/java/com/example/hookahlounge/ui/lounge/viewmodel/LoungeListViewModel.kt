package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoungeListViewModel @Inject constructor(): BaseViewModel<LoungeListUiState>() {
    override val initialState: LoungeListUiState
        get() = LoungeListUiState()

}

data class LoungeListUiState(
    val list: List<Lounge> = listOf(),
    val isLoading: Boolean = false,
)