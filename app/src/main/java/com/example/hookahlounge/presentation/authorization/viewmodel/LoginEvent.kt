package com.example.hookahlounge.presentation.authorization.viewmodel

import com.example.hookahlounge.domain.model.Lounge

sealed class LoginEvent{

    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
    data class SelectedWorkplace(val value: Lounge) : LoginEvent()
}
