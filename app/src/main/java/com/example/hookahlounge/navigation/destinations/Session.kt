package com.example.hookahlounge.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.example.hookahlounge.navigation.NonDismissableDialog
import com.example.hookahlounge.navigation.SessionNavGraph
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.navigation.navigator.SessionNavigator
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.example.hookahlounge.presentation.session.SessionDialog
import com.example.hookahlounge.presentation.session.SessionListScreen
import com.ramcosta.composedestinations.annotation.Destination
import ua.wwind.wms.core.ui.AppState

@SessionNavGraph(start = true)
@Destination
@Composable
fun NavSessionList(appState: AppState, navigator: SessionNavigator, menuItems: List<MenuItem>) {
    HookahScaffold(
        title = "Sessions",
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        navigateUp = { navigator.navigateUp() },
        floatingButton = {
            FloatingActionButton(
                onClick = { navigator.toSession() },
            ) {
                Icon(Icons.Filled.Add, "Add table to lounge")
            }
        },
    ) {
        SessionListScreen(toSession = { id: Long -> navigator.toSession(id) })
    }
}

@SessionNavGraph
@Destination(style = NonDismissableDialog::class)
@Composable
fun NavSession(
    navigator: LoungeNavigator,
    id: Long,
) {

    ElevatedCard {
        SessionDialog(id = id) { navigator.navigateUp() }
    }

}
