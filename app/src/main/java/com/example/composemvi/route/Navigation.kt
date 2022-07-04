package com.example.composemvi.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemvi.presentation.event.EventScreen
import com.example.composemvi.presentation.eventid.EventIdScreen
import com.example.composemvi.presentation.main.MainScreen
import com.example.composemvi.presentation.model.Event
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoute.ROUTE_MAIN) {

        composable(NavigationRoute.ROUTE_MAIN) {
            MainScreen(navController = navController)
        }

        composable(
//            NavigationRoute.ROUTE_ID + "?${NavigationArguments.ARGUMENT_EVENT_ID}" +
//                    "={${NavigationArguments.ARGUMENT_EVENT_ID}}",
            destinationString(
                NavigationRoute.ROUTE_EVENT_ID,
                NavigationArguments.ARGUMENT_EVENT_ID
            ),
            arguments = listOf(
                navArgument(NavigationArguments.ARGUMENT_EVENT_ID) {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {
            EventIdScreen(
                navController = navController,
                eventId = it.arguments?.getInt(NavigationArguments.ARGUMENT_EVENT_ID)
            )
        }

        composable(
//            NavigationRoute.ROUTE_EVENT + "?${NavigationArguments.ARGUMENT_EVENT}" +
//                    "={${NavigationArguments.ARGUMENT_EVENT}}",
            destinationString(NavigationRoute.ROUTE_EVENT, NavigationArguments.ARGUMENT_EVENT),
            arguments = listOf(
                navArgument(NavigationArguments.ARGUMENT_EVENT) {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                }
            )
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