package com.example.hookahlounge.presentation.hookah_ui_elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.example.hookahlounge.presentation.navigationdrawer.NavigationDrawer
import kotlinx.coroutines.launch

@Composable
fun HookahScaffold(
    title: String = "Lounge",
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    currentUser: User,
    navigateUp: () -> Unit,
    menuItems: List<MenuItem>,
    floatingButtonPosition: FabPosition = FabPosition.End,
    actions: @Composable () -> (Unit) = {},
    floatingButton: @Composable () -> (Unit) = {},
    bottomBar: @Composable () -> (Unit) = {},
    content: @Composable () -> (Unit),
) {
    val scope = rememberCoroutineScope()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { NavigationDrawer(menuItems, currentUser) }
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        HookahTopAppBar(
                            title = title,
                            navigateUp = navigateUp,
                            openDrawer = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            actions = actions
                        )
                    },
                    bottomBar = bottomBar,
                    floatingActionButton = floatingButton,
                    floatingActionButtonPosition = floatingButtonPosition,
                    snackbarHost = { SnackbarHost(snackbarHostState) })
                {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HookahTopAppBar(
    title: String = "Lounge",
    navigateUp: () -> (Unit),
    openDrawer: () -> (Unit),
    actions: @Composable () -> (Unit),
) {
    TopAppBar(modifier = Modifier
        .fillMaxWidth(),
        title = {
            Text(text = title)
        },
        actions = {
            actions()
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Toggle drawer",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

@Composable
fun HookahBottomBar(content: @Composable () -> (Unit)) {
    BottomAppBar(Modifier) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            content()
        }
    }
}