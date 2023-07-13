package com.example.hookahlounge.presentation.authorization.viewmodel

import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.User

data class LoginUiState(
    val isLoading: Boolean = false,
    val isAuthorized: Boolean = false,
    val user: User = User(),
    val lounges: List<Lounge> = listOf(),
)