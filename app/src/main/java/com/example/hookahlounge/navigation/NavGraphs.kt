package com.example.hookahlounge.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.hookahlounge.navigation.destinations.NavGraph
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
            NavLoungeDestination,
            NavTableDialogScreenDestination,
            NavMenuDestination,
            NavMenuDialogDestination,
            NavTobaccoDialogDestination,
            NavTobaccoListDestination

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
        startRoute = lounge,
        destinations = listOf(

        ),
        nestedNavGraphs = listOf(
            lounge,
            order,
            session
        )
    )

}

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        if (NavGraphs.root.route == destination.route) return NavGraphs.root
        NavGraphs.root.nestedNavGraphs.forEach { navGraph ->
            if (destination.route == navGraph.route) {
                return navGraph
            }
        }
    }

    throw RuntimeException("Unknown nav graph for destination $route")
}

fun DependenciesContainerBuilder<*>.currentNavigator(): CommonNavigator {
    return CommonNavigator(
        navBackStackEntry.destination.navGraph(),
        navController
    )
}

private val NavDestination.hostNavGraph: androidx.navigation.NavGraph
    get() = hierarchy.first { it is androidx.navigation.NavGraph } as androidx.navigation.NavGraph

@ExperimentalAnimationApi
fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultEnterTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry,
): EnterTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeIn()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeIn() + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
fun AnimatedContentTransitionScope<*>.defaultExitTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry,
): ExitTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeOut()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeOut() + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
fun AnimatedContentTransitionScope<*>.defaultPopEnterTransition(): EnterTransition {
    return fadeIn() + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)
}

@ExperimentalAnimationApi
fun AnimatedContentTransitionScope<*>.defaultPopExitTransition(): ExitTransition {
    return fadeOut() + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)
}