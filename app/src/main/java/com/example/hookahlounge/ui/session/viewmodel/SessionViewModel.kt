package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.util.BaseViewModel
import javax.inject.Inject

class SessionViewModel  @Inject constructor(): BaseViewModel<SessionUiState>() {
    override val initialState: SessionUiState
        get() = SessionUiState()
}

data class SessionUiState(
    val list: Session = Session(),
    val isLoading: Boolean = false,
)