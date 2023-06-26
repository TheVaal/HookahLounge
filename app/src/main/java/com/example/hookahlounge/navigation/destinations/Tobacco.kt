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
import com.example.hookahlounge.domain.model.Tobacco
import com.example.hookahlounge.domain.util.AppState
import com.example.hookahlounge.navigation.LoungeNavGraph
import com.example.hookahlounge.navigation.NonDismissableDialog
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahBottomBar
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahScaffold
import com.example.hookahlounge.presentation.menu.MenuDialog
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.example.hookahlounge.presentation.tobacco.TobaccoDialog
import com.example.hookahlounge.presentation.tobacco.TobaccoListScreen
import com.ramcosta.composedestinations.annotation.Destination

@LoungeNavGraph
@Destination
@Composable
fun NavTobaccoList(
    loungeId: Long?,
    appState: AppState,
    navigator: LoungeNavigator,
    menuItems: List<MenuItem>,
) {
    var openBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetAction = { openBottomSheet = !openBottomSheet }
    val toLoungeTobacco = { tobaccoId: Long?, loungeTobaccoId: Long? ->
        if (loungeId != null) {
            if (tobaccoId != null) {
                navigator.toLoungeTobacco(
                    loungeId = loungeId,
                    tobaccoId = tobaccoId
                )
            } else if (loungeTobaccoId != null) {
                navigator.toLoungeTobacco(
                    loungeId = loungeId,
                    loungeTobaccoId = loungeTobaccoId
                )
            } else {
                navigator.toLoungeTobacco(loungeId = loungeId)
            }
        }
    }
    HookahScaffold(
        title = "Tobacco",
        snackbarHostState = appState.snackbarHostState,
        drawerState = appState.drawerState,
        menuItems = menuItems,
        currentUser = appState.currentUser,
        bottomBar = {
            HookahBottomBar {
                LoungeTobaccoBottomBarContent(
                    openMenuSheet = bottomSheetAction
                )
            }
        },
        navigateUp = { navigator.navigateUp() },
    ) {
        TobaccoListScreen(
            loungeId = loungeId,
            openBottomSheet = openBottomSheet,
            bottomSheetAction = bottomSheetAction,
            toLoungeTobacco = toLoungeTobacco
        )
    }

}

@Composable
private fun LoungeTobaccoBottomBarContent(
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
fun NavTobaccoDialog(
    loungeTobaccoId: Long = 0L,
    tobaccoId: Long = 0L,
    loungeId: Long = 0L,
    navigator: LoungeNavigator,
) {
    ElevatedCard {
        TobaccoDialog(
            loungeTobaccoId = loungeTobaccoId,
            tobaccoId = tobaccoId,
            loungeId = loungeId,
        ) {
            navigator.navigateUp()
        }
    }
}

