package com.example.hookahlounge.presentation.session.viewmodel

import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Session

data class SessionUiState(
    val session: Session = Session(),
    val isLoading: Boolean = false,
    val lounges: List<Lounge> = listOf(),
) {
    companion object {
        fun getStatusList(): List<SessionStatus> {
            return listOf(
                SessionStatus.BOOKED,
                SessionStatus.CANCELED,
                SessionStatus.PREORDERED,
                SessionStatus.PAID
            )
        }
    }
}