package com.example.hookahlounge.navigation.destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hookahlounge.domain.util.AppState
import com.example.hookahlounge.navigation.LoungeNavGraph
import com.example.hookahlounge.navigation.NonDismissableDialog
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahBottomBar
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.presentation.menu.MenuDialog
import com.example.hookahlounge.presentation.menu.MenuListScreen
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.ramcosta.composedestinations.annotation.Destination

@LoungeNavGraph
@Destination
@Composable
fun NavMenu(
    loungeId: Long?,
    appState: AppState,
    navigator: LoungeNavigator,
    menuItems: List<MenuItem>,
) {
    var openBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetAction = { openBottomSheet = !openBottomSheet }
    val toLoungeMenu = { menuId: Long?, loungeMenuId: Long? ->
        if (loungeId != null) {
            if (menuId != null) {
                navigator.toLoungeMenu(
                    loungeId = loungeId,
                    menuId = menuId
                )
            } else if (loungeMenuId != null) {
                navigator.toLoungeMenu(
                    loungeId = loungeId,
                    loungeMenuId = loungeMenuId
                )
            } else {
                navigator.toLoungeMenu(loungeId = loungeId)
            }
        }
    }
    HookahScaffold(
        title = "Lounge",
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        bottomBar = {
            HookahBottomBar {
                LoungeMenuBottomBarContent(
                    openMenuSheet = bottomSheetAction
                )
            }
        },
        navigateUp = { navigator.navigateUp() },
    ) {
        MenuListScreen(
            loungeId = loungeId,
            openBottomSheet = openBottomSheet,
            bottomSheetAction = bottomSheetAction,
            toLoungeMenu = toLoungeMenu
        )
    }

}

@Composable
private fun LoungeMenuBottomBarContent(
    openMenuSheet: () -> (Unit),
) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = openMenuSheet
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Toggle drawer",
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text("Add", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@LoungeNavGraph
@Destination(style = NonDismissableDialog::class)
@Composable
fun NavMenuDialog(
    loungeMenuId: Long = 0L,
    menuId: Long = 0L,
    loungeId: Long = 0L,
    navigator: LoungeNavigator,
) {
    ElevatedCard {
        MenuDialog(
            loungeMenuId = loungeMenuId,
            menuId = menuId,
            loungeId = loungeId,
        ) {
            navigator.navigateUp()
        }
    }
}