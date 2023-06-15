package com.example.hookahlounge.presentation.session.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.mappers.toSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SessionListViewModel @Inject constructor(pager: Pager<Int, SessionEntity>): ViewModel() {
    val loungePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toSession() }
        }.cachedIn(viewModelScope)

}
