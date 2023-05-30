package com.example.hookahlounge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hookahlounge.navigation.NavGraphs
import com.example.hookahlounge.navigation.currentNavigator
import com.example.hookahlounge.ui.lounge.LoungeListScreen
import com.example.hookahlounge.ui.navigationdrawer.MenuItem
import com.example.hookahlounge.ui.navigationdrawer.NavigationDrawer
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import ua.wwind.wms.core.ui.rememberAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HookahLoungeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(rememberAppState(appContext = applicationContext))
                    val currentNavigator = currentNavigator()
                    dependency(currentNavigator)
                    dependency(MenuItem.DrawerMenu(currentNavigator))
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HookahLoungeTheme {
        LoungeListScreen({})
    }
}