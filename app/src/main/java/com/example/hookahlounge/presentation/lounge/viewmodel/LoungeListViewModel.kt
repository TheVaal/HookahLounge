package com.example.hookahlounge.presentation.lounge.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.mappers.toLounge
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LoungeListViewModel @Inject constructor(
    pager: Pager<Int, LoungeEntity>,
) : BaseViewModel<LoungeListUiState>() {
    val loungePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toLounge() }
        }.cachedIn(viewModelScope)
    override val initialState: LoungeListUiState
        get() = LoungeListUiState()

}

data class LoungeListUiState(
    val list: List<Lounge> = listOf(),
    val isLoading: Boolean = false,
)