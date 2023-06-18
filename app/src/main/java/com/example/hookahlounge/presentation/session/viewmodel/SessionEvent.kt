package com.example.hookahlounge.presentation.session.viewmodel

import com.example.hookahlounge.domain.model.Lounge

sealed class SessionEvent {
    data class EnteredName(val value: String) : SessionEvent()
    data class EnteredPhone(val value: String) : SessionEvent()
    data class EnteredStatus(val value: String) : SessionEvent()
    data class EnteredDate(val value: String) : SessionEvent()
    data class EnteredTime(val value: String) : SessionEvent()
    data class EnteredLounge(val value: Lounge) : SessionEvent()
    data class EnteredCountry(val value: String) : SessionEvent()
}
