package com.example.hookahlounge.navigation.destinations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.domain.util.AppState
import com.example.hookahlounge.navigation.OrderNavGraph
import com.example.hookahlounge.navigation.navigator.OrderNavigator
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.example.hookahlounge.presentation.order.OrderListScreen
import com.example.hookahlounge.presentation.order.OrderScreen
import com.example.hookahlounge.presentation.order.viewmodel.OrderUIEvent
import com.example.hookahlounge.presentation.order.viewmodel.OrderViewModel
import com.ramcosta.composedestinations.annotation.Destination

@OrderNavGraph(start = true)
@Destination
@Composable
fun NavOrderList(appState: AppState, navigator: OrderNavigator, menuItems: List<MenuItem>) {
    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() },
        floatingButton = {
            FloatingActionButton(
                onClick = { navigator.toOrder(0L) },
            ) {
                Icon(Icons.Filled.Add, "Add table to lounge")
            }
        },
    ) {
        OrderListScreen(toOrder = { orderId: Long -> navigator.toOrder(orderId) })
    }
}

@OrderNavGraph
@Destination
@Composable
fun NavOrder(
    appState: AppState,
    navigator: OrderNavigator,
    id: Long = 0L,
    menuItems: List<MenuItem>,
) {
    val viewModel: OrderViewModel = hiltViewModel()

    HookahScaffold(
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() },
        actions = {
            IconButton(onClick = {
                viewModel.onEvent(OrderUIEvent.PostOrder)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Post/Edit order",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    ) {

        val state = viewModel.uiState.collectAsStateWithLifecycle()
        LaunchedEffect(true) {
            viewModel.loadData(id)
        }

        Box(modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp)) {
            OrderScreen(state.value) { event -> viewModel.onEvent(event = event) }
        }
    }
}
