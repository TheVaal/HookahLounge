package com.example.hookahlounge.domain.util

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.domain.model.User
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    appContext: Context,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberAnimatedNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    currentUser: User = remember{
        User(
            login = "val",
        )
    },
    lounge: Lounge = remember{Lounge()},

    ): AppState {
    return remember(
        navController,
        coroutineScope,
        snackbarHostState,
        lounge
    ) {
        AppState(
            navController = navController,
            appContext = appContext,
            coroutineScope = coroutineScope,
            snackbarHostState = snackbarHostState,
            drawerState = drawerState,
            currentUser = currentUser
        )
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    val appContext: Context,
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope,
    val drawerState: DrawerState,
    val currentUser: User,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val previousDestination: NavDestination?
        @Composable get() = navController
            .previousBackStackEntry?.destination

    var shouldShowSettingsDialog by mutableStateOf(false)
        private set

    var lounge by mutableStateOf(Lounge())

    fun setShowSettingsDialog(shouldShow: Boolean) {
        shouldShowSettingsDialog = shouldShow
    }

    fun showSnackbar(snackbarMessage: SnackbarMessage) {
        coroutineScope.launch {
            val message = when (snackbarMessage) {
                is SnackbarMessage.ResourceMessage -> appContext.getString(
                    snackbarMessage.resourceId,
                    *snackbarMessage.args.toTypedArray()
                )
                is SnackbarMessage.StringMessage -> snackbarMessage.message
            }
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = snackbarMessage.actionLabel,
                duration = snackbarMessage.duration
            )
            if (result == SnackbarResult.ActionPerformed) snackbarMessage.onAction?.invoke()
            else snackbarMessage.onDismiss?.invoke()
        }
    }
}
