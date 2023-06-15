package com.example.hookahlounge.presentation.lounge.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoungeViewModel @Inject constructor(
    private val loungeUseCase: LoungeUseCase,
    private val dispatchers: AppCoroutineDispatchers,
) : BaseViewModel<LoungeUiState>() {
    override val initialState: LoungeUiState
        get() = LoungeUiState()

    fun loadData(id: Long?) {
        if (id == null) return
        viewModelScope.launch(dispatchers.io + job) {
            state = state.copy(isLoading = true)
            loungeUseCase.loadLoungeById(id).collect {
                it.onSuccess { lounge ->
                    state = state.copy(
                        isLoading = false,
                        lounge = lounge
                    )
                }
            }
        }
    }

    fun postLounge() {
        state = state.copy(
            isLoading = true
        )

        viewModelScope.launch(dispatchers.io + job) {
            val result = if (state.lounge.id == 0L) {
                loungeUseCase.postLounge(state.lounge)
            } else {
                loungeUseCase.putLounge(state.lounge)
            }
            result.onSuccess {
                editLounge(it)
            }

            state = state.copy(
                isLoading = false
            )


        }
    }

    private fun editLounge(lounge: Lounge) {
        state = state.copy(lounge = lounge)
    }

    fun onEvent(event: LoungeEvent) {
        when (event) {
            is LoungeEvent.EnteredAddress -> {
                editLounge(state.lounge.copy(address = event.value))
            }

            is LoungeEvent.EnteredCity -> {
                editLounge(state.lounge.copy(city = event.value))
            }

            is LoungeEvent.EnteredCountry -> {
                editLounge(state.lounge.copy(country = event.value))
            }

            is LoungeEvent.EnteredName -> {
                editLounge(state.lounge.copy(name = event.value))
            }

            is LoungeEvent.EnteredPostalCode -> {
                editLounge(state.lounge.copy(postalCode = event.value))
            }

            is LoungeEvent.EnteredState -> {
                editLounge(state.lounge.copy(state = event.value))
            }
        }
    }

}

data class LoungeUiState(
    val lounge: Lounge = Lounge(),
    val isLoading: Boolean = false,
    val allowTableEdit: Boolean = lounge.id!=0L
)