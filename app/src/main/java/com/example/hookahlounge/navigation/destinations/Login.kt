package com.example.hookahlounge.navigation.destinations

import androidx.compose.runtime.Composable
import com.example.hookahlounge.navigation.LoungeNavGraph
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.presentation.authorization.UserLoginScreen
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@LoungeNavGraph(start = true)
@Composable
fun LoginScreen(navigator: LoungeNavigator) {
    UserLoginScreen(toLounge = { id -> navigator.toLounge(id) })
}