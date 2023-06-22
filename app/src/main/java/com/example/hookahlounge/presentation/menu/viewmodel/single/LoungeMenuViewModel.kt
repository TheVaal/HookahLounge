package com.example.hookahlounge.presentation.menu.viewmodel.single

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.usecase.LoungeMenuUseCase
import com.example.hookahlounge.domain.usecase.MenuUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoungeMenuViewModel @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val loungeMenuUseCase: LoungeMenuUseCase,
    private val menuUseCase: MenuUseCase,

    ) : BaseViewModel<LoungeMenuUiState>() {
    override val initialState: LoungeMenuUiState
        get() = LoungeMenuUiState()

    fun loadData(
        loungeId: Long,
        menuId: Long,
        loungeMenuId: Long,
    ) {
        if (loungeId == 0L && loungeMenuId == 0L && menuId == 0L) return
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {
            if (menuId != 0L) {
                loadByMenu(menuId)

            } else if (loungeMenuId != 0L) {
                loadByLoungeMenu(loungeMenuId)
            }
        }
        editLoungeMenu(state.loungeMenu.copy(loungeId = loungeId))
        state = state.copy(isLoading = false)

    }

    fun onEvent(event: LoungeMenuEvent) {
        when (event) {
            is LoungeMenuEvent.EnteredName -> {
                val menu = state.loungeMenu.menu.copy(name = event.value)
                editLoungeMenu(state.loungeMenu.copy(menu = menu))
            }

            is LoungeMenuEvent.EnteredPrice -> {
                editLoungeMenu(state.loungeMenu.copy(price = event.value))
            }
        }

    }

    fun postMenu() {
        viewModelScope.launch(dispatchers.io + job) {
            if (state.loungeMenu.menu.id == 0L) {
                menuUseCase.postMenu(state.loungeMenu.menu).onSuccess { menu ->
                    editLoungeMenu(state.loungeMenu.copy(menu = menu, menuId = menu.id))
                }
            } else {
                menuUseCase.putMenu(state.loungeMenu.menu)
            }.onSuccess {
                if (state.loungeMenu.id == 0L){
                    loungeMenuUseCase.postMenu(loungeMenu = state.loungeMenu)
                } else{
                    loungeMenuUseCase.putLoungeMenu(loungeMenu = state.loungeMenu)

                }
            }
        }
    }


    private fun editLoungeMenu(loungeMenu: LoungeMenu) {
        state = state.copy(loungeMenu = loungeMenu)
    }

    private suspend fun loadByLoungeMenu(loungeMenuId: Long) {
        loungeMenuUseCase.loadMenuById(loungeMenuId).collect {
            it.onSuccess { loungeMenu ->
                state = state.copy(loungeMenu = loungeMenu)
            }
        }
    }

    private suspend fun loadByMenu(menuId: Long) {
        menuUseCase.loadMenuById(menuId).collect {
            it.onSuccess { menu ->
                editLoungeMenu(state.loungeMenu.copy(menu = menu, menuId = menuId))
            }
        }
    }
}
