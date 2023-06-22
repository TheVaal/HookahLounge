package com.example.hookahlounge.presentation.menu.viewmodel.list

import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.model.Menu

data class LoungeMenuListUiState(
    val isLoading: Boolean = false,
    val loungeMenuList: List<LoungeMenu> = listOf(),
    val menuList: List<Menu> = listOf(),
) {
    val availableToAdd: List<Menu> = menuList.filter { menu ->
        loungeMenuList.none { it.menuId == menu.id }
    }
}