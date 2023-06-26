package com.example.hookahlounge.presentation.order.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.data.mappers.toOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    pager: Pager<Int, OrderWithFields>,
) : ViewModel() {
    val loungePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toOrder() }
        }.cachedIn(viewModelScope)
}
