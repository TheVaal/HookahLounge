package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.util.BaseViewModel
import javax.inject.Inject

class TableViewModel  @Inject constructor(): BaseViewModel<SessionUiState>() {
    override val initialState: SessionUiState
        get() = SessionUiState()

}

data class TableUiState(
    val list: Table = Table(),
    val isLoading: Boolean = false,
)