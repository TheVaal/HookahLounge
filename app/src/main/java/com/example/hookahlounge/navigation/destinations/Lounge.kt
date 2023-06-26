package com.example.hookahlounge.navigation.destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.navigation.LoungeNavGraph
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahBottomBar
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.presentation.lounge.LoungeListScreen
import com.example.hookahlounge.presentation.lounge.LoungeScreen
import com.example.hookahlounge.presentation.lounge.viewmodel.LoungeEvent
import com.example.hookahlounge.presentation.lounge.viewmodel.LoungeViewModel
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.ramcosta.composedestinations.annotation.Destination
import com.example.hookahlounge.domain.util.AppState

@LoungeNavGraph(start = true)
@Destination
@Composable
fun NavLoungeList(appState: AppState, navigator: LoungeNavigator, menuItems: List<MenuItem>) {

    HookahScaffold(
        title = "Lounges",
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() },
        floatingButton = {
            FloatingActionButton(
                onClick = { navigator.toLounge(null) },
            ) {
                Icon(Icons.Filled.Add, "Add table to lounge")
            }
        },
    ) {
        LoungeListScreen { id: Long -> navigator.toLounge(id) }
    }
}

@LoungeNavGraph
@Destination
@Composable
fun NavLounge(
    appState: AppState,
    navigator: LoungeNavigator,
    id: Long?,
    menuItems: List<MenuItem>,
) {
    val viewModel: LoungeViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val currId = if (state.value.lounge.id != id && state.value.lounge.id != 0L) {
        state.value.lounge.id
    } else {
        id
    }
    LaunchedEffect(state.value.lounge.id != id && state.value.lounge.id != 0L) {
        viewModel.loadData(currId)
    }

    HookahScaffold(
        title = "Lounge",
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() },
        bottomBar = {
            HookahBottomBar {
                LoungeBottomBarContent(enabled = (currId != null),
                    addTable = { navigator.toNewTable(currId) },
                    openLoungeMenuSheet = {navigator.toMenuList(currId)},
                    openLoungeTobaccoSheet = {navigator.toTobaccoList(currId)}
                )
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.postLounge()
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Post/Edit lounge",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    ) {
        LoungeScreen(
            state = state.value,
            toTable = { id: Long -> navigator.toTable(id) },
            onEvent = { event: LoungeEvent -> viewModel.onEvent(event) }
        )

    }
}

@Composable
private fun LoungeBottomBarContent(
    enabled: Boolean,
    addTable: () -> (Unit),
    openLoungeMenuSheet: () -> (Unit),
    openLoungeTobaccoSheet: () -> (Unit),
) {
    TextButton(
        enabled = enabled,
        onClick = openLoungeMenuSheet
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Toggle drawer",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text("Menu", style = MaterialTheme.typography.labelSmall)
        }
    }
    TextButton(
        enabled = enabled,
        onClick = addTable
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Toggle drawer",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text("Add", style = MaterialTheme.typography.labelSmall)
        }
    }
    TextButton(
        enabled = enabled,
        onClick = openLoungeTobaccoSheet
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Toggle drawer",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text("Tobacco", style = MaterialTheme.typography.labelSmall)
        }

    }
}

