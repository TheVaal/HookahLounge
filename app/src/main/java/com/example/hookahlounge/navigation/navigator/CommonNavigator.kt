package com.example.hookahlounge.navigation.navigator

import androidx.navigation.NavController
import com.example.hookahlounge.navigation.destinations.destinations.NavLoungeDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavLoungeListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavOrderDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavOrderListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavSessionDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavSessionListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavTableDialogScreenDestination
import com.example.hookahlounge.domain.util.BaseNavigator
import com.ramcosta.composedestinations.navigation.navigate

class CommonNavigator(
    private val navController: NavController,
) : BaseNavigator, LoungeNavigator, SessionNavigator, OrderNavigator {
    override fun toOrders() {
        navController.navigate(NavOrderListDestination)
    }

    override fun toOrder(id: Long?) {
        navController.navigate(NavOrderDestination(id = id))
    }

    override fun toLounges() {
        navController.navigate(NavLoungeListDestination)
    }

    override fun toLounge(id: Long?) {
        navController.navigate(NavLoungeDestination(id = id))
    }

    override fun toTable(id: Long) {
        navController.navigate(NavTableDialogScreenDestination(id = id, loungeId = null))
    }

    override fun toNewTable(loungeId: Long?) {
        navController.navigate(NavTableDialogScreenDestination(id = null, loungeId = loungeId))
    }

    override fun toSessions() {
        navController.navigate(NavSessionListDestination)
    }

    override fun toSession(id:Long) {
        navController.navigate(NavSessionDestination(id))
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun openQuickSettings() {
        TODO("Not yet implemented")
    }
}
