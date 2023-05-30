package com.example.hookahlounge.navigation.destinations

import androidx.compose.runtime.Composable
import com.example.hookahlounge.navigation.LoungeNavGraph
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.ui.lounge.LoungeListScreen
import com.example.hookahlounge.ui.lounge.LoungeScreen
import com.example.hookahlounge.ui.navigationdrawer.MenuItem
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahScaffold
import com.ramcosta.composedestinations.annotation.Destination
import ua.wwind.wms.core.ui.AppState

@LoungeNavGraph(start = true)
@Destination
@Composable
fun NavLoungeList(appState: AppState, navigator: LoungeNavigator, menuItems:List<MenuItem>) {

    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        navigateUp = { navigator.navigateUp() }
    ) {
        LoungeListScreen { navigator.toLounge(0L) }
    }


}

@LoungeNavGraph
@Destination
@Composable
fun NavLounge(appState: AppState, navigator: LoungeNavigator, id: Long?, menuItems:List<MenuItem>) {
    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        navigateUp = { navigator.navigateUp() }
    ) {
        LoungeScreen(id)
    }
}

