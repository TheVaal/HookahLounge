package ua.wwind.wms.core.ui

import android.content.Context
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
import androidx.navigation.compose.rememberNavController
import com.example.hookahlounge.domain.model.User
import com.example.hookahlounge.domain.util.SnackbarMessage
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    appContext: Context,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    currentUser: User = remember{
        User(
            login = "vaal",
            id = 1L,
            name = "Valerii Krivoruchko",
            phone = "+380684181893"
        )
    },

    ): AppState {
    return remember(
        navController,
        coroutineScope,
        snackbarHostState
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
