package com.example.hookahlounge.presentation.order.viewmodel

import com.example.hookahlounge.domain.model.InOrder
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.Mix
import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.domain.model.Session

data class OrderUiState(
    val isLoading: Boolean = false,
    val order: Order = Order(),
    val inOrder: List<InOrder> = listOf(),
    val mixes: List<Mix> = listOf(),
    val lounge: Lounge = Lounge(),
    val guests: String = "1",
    val sessions: List<Session> = listOf(),
)