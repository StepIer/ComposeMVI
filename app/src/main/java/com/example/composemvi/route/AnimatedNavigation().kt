package com.example.composemvi.route

import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.example.composemvi.presentation.event.EventScreen
import com.example.composemvi.presentation.eventid.EventIdScreen
import com.example.composemvi.presentation.main.MainScreen
import com.example.composemvi.presentation.model.Event
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
val tweenSpec =
    tween<IntOffset>(durationMillis = 2000, easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f))

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = NavigationRoute.ROUTE_MAIN) {

        composable(NavigationRoute.ROUTE_MAIN) {
            MainScreen(navController = navController)
        }

        composable(
            destinationString(
                NavigationRoute.ROUTE_EVENT_ID,
                NavigationArguments.ARGUMENT_EVENT_ID
            ),
            enterTransition = { initial, _ ->
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = { _, target ->
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = { initial, _ ->
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = { _, target ->
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
            }
        ) {
            EventIdScreen(
                navController = navController,
                eventId = it.arguments?.getString(NavigationArguments.ARGUMENT_EVENT_ID)
                    ?.toIntOrNull()
            )
        }

        composable(
            destinationString(NavigationRoute.ROUTE_EVENT, NavigationArguments.ARGUMENT_EVENT),
            enterTransition = { initial, _ ->
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = tweenSpec)
            },
            exitTransition = { _, target ->
                slideOutVertically(targetOffsetY = { -1000 }, animationSpec = tweenSpec)
            },
            popEnterTransition = { initial, _ ->
                slideInVertically(initialOffsetY = { -1000 }, animationSpec = tweenSpec)
            },
            popExitTransition = { _, target ->
                slideOutVertically(targetOffsetY = { 1000 }, animationSpec = tweenSpec)
            }
        ) {
            EventScreen(
                navController = navController,
                event = it.arguments?.getString(NavigationArguments.ARGUMENT_EVENT)
                    ?.let { eventJson ->
                        Json.decodeFromString<Event>(eventJson)
                    }
            )
        }
    }
}