package com.example.hookahlounge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.navigation.plusAssign
import com.example.hookahlounge.domain.util.rememberAppState
import com.example.hookahlounge.navigation.NavGraphs
import com.example.hookahlounge.navigation.currentNavigator
import com.example.hookahlounge.navigation.defaultEnterTransition
import com.example.hookahlounge.navigation.defaultExitTransition
import com.example.hookahlounge.navigation.defaultPopEnterTransition
import com.example.hookahlounge.navigation.defaultPopExitTransition
import com.example.hookahlounge.presentation.navigationdrawer.MenuItem
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalAnimationApi::class,
        ExperimentalMaterialNavigationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HookahLoungeTheme {
                val navController = rememberAnimatedNavController()

                val bottomSheetNavigator = rememberBottomSheetNavigator()
                navController.navigatorProvider += bottomSheetNavigator

                ModalBottomSheetLayout(
                    bottomSheetNavigator = bottomSheetNavigator,
                    // other configuration for you bottom sheet screens, like:
                    sheetShape = RoundedCornerShape(16.dp),
                ) {
                    // ...
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        // engine required for the BottomSheet
                        engine = rememberAnimatedNavHostEngine(
                            rootDefaultAnimations = RootNavGraphDefaultAnimations(
                                enterTransition = {
                                    defaultEnterTransition(initialState, targetState)
                                },
                                exitTransition = {
                                    defaultExitTransition(initialState, targetState)
                                },
                                popEnterTransition = { defaultPopEnterTransition() },
                                popExitTransition = { defaultPopExitTransition() }
                            )
                        ),
                        dependenciesContainerBuilder = {
                            dependency(
                                rememberAppState(
                                    appContext = applicationContext,
                                    navController = navController
                                )
                            )
                            val currentNavigator = currentNavigator()
                            dependency(currentNavigator)
                            dependency(MenuItem.DrawerMenu(currentNavigator))
                        }
                        // ...
                    )
                }
            }
        }
    }
}
