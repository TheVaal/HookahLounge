package com.example.hookahlounge.ui.lounge.viewmodel

import com.example.hookahlounge.domain.model.Order
import com.example.hookahlounge.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(): BaseViewModel<OrderListUiState>() {
    override val initialState: OrderListUiState
        get() = OrderListUiState()

}

data class OrderListUiState(
    val list: List<Order> = listOf(),
    val isLoading: Boolean = false,
)