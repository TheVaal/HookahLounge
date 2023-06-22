package com.example.hookahlounge.presentation.menu.viewmodel.list

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.usecase.LoungeMenuUseCase
import com.example.hookahlounge.domain.usecase.MenuUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoungeMenuListViewModel @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val loungeMenuUseCase: LoungeMenuUseCase,
    private val menuUseCase: MenuUseCase,

    ) : BaseViewModel<LoungeMenuListUiState>() {
    override val initialState: LoungeMenuListUiState
        get() = LoungeMenuListUiState()

    fun loadData(loungeId: Long?) {
        if (loungeId == null) return
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {
            state = state.copy(isLoading = true)
            menuUseCase.loadMenus().collect { menus ->
                menus.onSuccess { menuData ->
                    loungeMenuUseCase.loadMenus(loungeId).collect { loungeMenuResponse ->
                        loungeMenuResponse.onSuccess { loungeMenuList ->
                            state = state.copy(
                                isLoading = false,
                                loungeMenuList = loungeMenuList,
                                menuList = menuData
                            )
                        }.onError {
                            state = state.copy(
                                isLoading = false,
                                menuList = menuData
                            )
                        }
                    }
                }.onError {
                    state = state.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}

