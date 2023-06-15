package com.example.hookahlounge.presentation.order.viewmodel

import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.domain.util.BaseViewModel
import javax.inject.Inject

class OrderViewModel  @Inject constructor(): BaseViewModel<OrderUiState>() {
    override val initialState: OrderUiState
        get() = OrderUiState()

}

data class OrderUiState(
    val list: Order = Order(),
    val isLoading: Boolean = false,
)