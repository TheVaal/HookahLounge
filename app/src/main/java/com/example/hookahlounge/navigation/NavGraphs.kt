package com.example.hookahlounge.navigation

import com.example.hookahlounge.navigation.destinations.NavGraph
import com.example.hookahlounge.navigation.destinations.destinations.NavLoungeDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavLoungeListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavOrderDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavOrderListDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavSessionDestination
import com.example.hookahlounge.navigation.destinations.destinations.NavSessionListDestination
import com.example.hookahlounge.navigation.navigator.CommonNavigator
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.spec.*

/**
 * Class generated if any Composable is annotated with `@Destination`.
 * It aggregates all [TypedDestination]s in their [NavGraph]s.
 */
object NavGraphs {

    val lounge: NavGraph = NavGraph(
        route = "lounge",
        startRoute = NavLoungeListDestination,
        destinations = listOf(
            NavLoungeListDestination,
            NavLoungeDestination

        )
    )
    private val order: NavGraph = NavGraph(
        route = "order",
        startRoute = NavOrderListDestination,
        destinations = listOf(
            NavOrderListDestination,
            NavOrderDestination

        )
    )

    private val session: NavGraph = NavGraph(
        route = "session",
        startRoute = NavSessionListDestination,
        destinations = listOf(
            NavSessionListDestination,
            NavSessionDestination

        )
    )
    val root: NavGraph = NavGraph(
        route = "root",
        startRoute = order,
        destinations = listOf(

        ),
        nestedNavGraphs = listOf(
            lounge,
            order,
            session
        )
    )

}

fun DependenciesContainerBuilder<*>.currentNavigator(): CommonNavigator {
    return CommonNavigator(
        navController
    )
}