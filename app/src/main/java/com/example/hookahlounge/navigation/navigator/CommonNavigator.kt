package com.example.hookahlounge.navigation.navigator

import androidx.navigation.NavController
import com.example.hookahlounge.domain.util.BaseNavigator
import com.example.hookahlounge.navigation.destinations.destinations.NavLoungeDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavLoungeListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavMenuDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavMenuDialogDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavOrderDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavOrderListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavSessionDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavSessionListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavTableDialogScreenDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavTobaccoDialogDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavTobaccoListDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CommonNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController,
) : BaseNavigator, LoungeNavigator, SessionNavigator, OrderNavigator {
    override fun toOrders() {
        navController.navigate(NavOrderListDestination)
    }

    override fun toOrder(id: Long) {
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

    override fun toMenuList(loungeId: Long?) {
        navController.navigate(NavMenuDestination(loungeId))
    }

    override fun toLoungeMenu(loungeMenuId: Long, menuId: Long, loungeId: Long) {
        navController.navigate(
            NavMenuDialogDestination(
                loungeMenuId = loungeMenuId,
                menuId = menuId,
                loungeId = loungeId
            )
        )
    }

    override fun toTobaccoList(loungeId: Long?) {
        navController.navigate(NavTobaccoListDestination(loungeId))
    }

    override fun toLoungeTobacco(loungeTobaccoId: Long, tobaccoId: Long, loungeId: Long) {
        navController.navigate(
            NavTobaccoDialogDestination(
                loungeTobaccoId = loungeTobaccoId,
                tobaccoId = tobaccoId,
                loungeId = loungeId
            )
        )
    }

    override fun toSessions() {
        navController.navigate(NavSessionListDestination)
    }

    override fun toSession(id: Long) {
        navController.navigate(NavSessionDestination(id))
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun openQuickSettings() {
        TODO("Not yet implemented")
    }
}
