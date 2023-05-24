package com.example.hookahlounge.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hookahlounge.domain.model.User

@Composable
fun HomeScreen() {
    val currentUser: User = User(
        login = "vaal",
        id = 1L,
        name = "Valerii Krivoruchko",
        phone = "+380684181893"
    )
    HomeScreen(currentUser)
}

@Composable
fun HomeScreen(user: User) {

}
