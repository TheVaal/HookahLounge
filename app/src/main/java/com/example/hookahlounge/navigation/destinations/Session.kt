package com.example.hookahlounge.navigation.destinations

import androidx.compose.runtime.Composable
import com.example.hookahlounge.navigation.SessionNavGraph
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.navigation.navigator.SessionNavigator
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.example.hookahlounge.presentation.session.SessionListScreen
import com.example.hookahlounge.presentation.session.SessionNewScreen
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahScaffold
import com.ramcosta.composedestinations.annotation.Destination
import ua.wwind.wms.core.ui.AppState

@SessionNavGraph(start = true)
@Destination
@Composable
fun NavSessionList(appState: AppState, navigator: SessionNavigator, menuItems:List<MenuItem>) {
    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() }
    ) {
        SessionListScreen( newSession = { navigator.toSession() } )
    }
}

@SessionNavGraph
@Destination
@Composable
fun NavSession(appState: AppState, navigator: LoungeNavigator, menuItems:List<MenuItem>) {
    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() }
    ) {
        SessionNewScreen()
    }
}
