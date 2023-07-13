package com.example.hookahlounge.presentation.authorization.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.repository.api.AuthorizationRepository
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
    private val loungeUseCase: LoungeUseCase,
    private val dispatchers: AppCoroutineDispatchers,
) : BaseViewModel<LoginUiState>() {
    override val initialState: LoginUiState
        get() = LoginUiState()

    fun loadData() {
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {

            authorizationRepository.getDataStoreData().collect { user ->
                loungeUseCase.loadLounges().collect { response ->
                    response.onSuccess { lounges ->
                        state = state.copy(
                            lounges = lounges,
                            isAuthorized = user.authToken.isNotEmpty() ,
                            isLoading = false,
                            user = user
                        )
                    }
                }
            }
        }
    }

    fun authorize() {
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {
            authorizationRepository.authorize(state.user).collect {
                it.onSuccess { user ->
                    state = state.copy(
                        isLoading = false,
                        user = user,
                        isAuthorized = user.authToken.isNotEmpty()
                    )
                }
            }

        }
    }

    fun onEvent(event: LoginEvent) {
        state = when (event) {
            is LoginEvent.EnteredEmail -> {
                state.copy(user = state.user.copy(login = event.value))
            }

            is LoginEvent.EnteredPassword ->
                state.copy(user = state.user.copy(password = event.value))

            is LoginEvent.SelectedWorkplace -> {
                val user = state.user.copy(currentWorkplace = event.value.id)
                viewModelScope.launch(dispatchers.io + job) {
                    authorizationRepository.updateDataStoreData(user)
                }
                state.copy(user = user)

            }
        }
    }
}

