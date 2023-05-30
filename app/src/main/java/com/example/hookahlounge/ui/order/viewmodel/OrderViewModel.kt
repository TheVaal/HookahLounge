package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.util.BaseViewModel
import javax.inject.Inject

class OrderViewModel  @Inject constructor(): BaseViewModel<OrderUiState>() {
    override val initialState: OrderUiState
        get() = OrderUiState()

}

data class OrderUiState(
    val list: Order = Order(),
    val isLoading: Boolean = false,
)