package com.example.hookahlounge.navigation.destinations

import androidx.compose.runtime.Composable
import com.example.hookahlounge.navigation.OrderNavGraph
import com.example.hookahlounge.navigation.navigator.OrderNavigator
import com.example.hookahlounge.ui.navigationdrawer.MenuItem
import com.example.hookahlounge.ui.order.OrderListScreen
import com.example.hookahlounge.ui.order.OrderScreen
import com.example.hookahlounge.ui.theme.hookah_ui_elements.HookahScaffold
import com.ramcosta.composedestinations.annotation.Destination
import ua.wwind.wms.core.ui.AppState

@OrderNavGraph(start = true)
@Destination
@Composable
fun NavOrderList(appState: AppState, navigator: OrderNavigator, menuItems:List<MenuItem>) {
    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() }
    ) {
        OrderListScreen { navigator.toOrder(0L) }
    }
}

@OrderNavGraph
@Destination
@Composable
fun NavOrder(appState: AppState, navigator: OrderNavigator, id: Long?, menuItems:List<MenuItem>) {
    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() }
    ) {
        OrderScreen(id)
    }
}
