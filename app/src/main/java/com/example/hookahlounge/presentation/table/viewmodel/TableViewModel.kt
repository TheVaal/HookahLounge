package com.example.hookahlounge.presentation.table.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.model.Table
import com.example.hookahlounge.domain.usecase.TableUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableUseCase: TableUseCase,
    private val dispatchers: AppCoroutineDispatchers,
) : BaseViewModel<TableUiState>() {
    override val initialState: TableUiState
        get() = TableUiState()

    fun loadData(id: Long? = null, loungeId: Long? = null) {

        if (loungeId != null) {
            state = state.copy(table = state.table.copy(loungeId = loungeId))
            state = state.copy(isLoading = false)
            return
        }
        if (id == null) return
        viewModelScope.launch(dispatchers.io + job) {
            state = state.copy(isLoading = true)
            tableUseCase.getTable(id = id).collect {
                it.onSuccess { table ->
                    state = state.copy(table = table)
                    state = state.copy(isLoading = false)
                }.onError {
                    state = state.copy(isLoading = false)
                }
            }
        }
    }

    private fun editTable(table: Table){
        state = state.copy(table = table)
    }

    fun onEvent(event: TableEvent){

        when(event){
            is TableEvent.EnteredSize -> {
                editTable(state.table.copy(seats = event.value))
            }

            is TableEvent.EnteredName -> {
                editTable(state.table.copy(name = event.value))
            }
        }
    }
    fun postTable() {
        state = state.copy(
            isLoading = true
        )
        viewModelScope.launch(dispatchers.io + job) {
            if (state.table.id == 0L) {
                tableUseCase.postTable(state.table)

            } else {
                tableUseCase.putTable(state.table)
            }
            state = state.copy(
                isLoading = false)
        }
    }
}

data class TableUiState(
    val table: Table = Table(),
    val isLoading: Boolean = false,
)