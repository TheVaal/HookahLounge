package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionListViewModel @Inject constructor(): BaseViewModel<SessionListUiState>() {
    override val initialState: SessionListUiState
        get() = SessionListUiState()

}

data class SessionListUiState(
    val list: List<Session> = listOf(),
    val isLoading: Boolean = false,
)