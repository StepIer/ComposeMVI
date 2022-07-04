package com.example.composemvi.presentation.main

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composemvi.R
import com.example.composemvi.presentation.main.components.ButtonTile
import com.example.composemvi.presentation.main.components.LazyColumnSwipeToDismiss
import com.example.composemvi.presentation.main.components.ListTile
import com.example.composemvi.presentation.model.Event
import com.example.composemvi.route.NavigationArguments
import com.example.composemvi.route.NavigationRoute
import com.example.composemvi.route.navigateString
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {

    val mainViewModel: MainViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val state = mainViewModel.state.collectAsState()
    val events: List<Event> = state.value.events.collectAsState(emptyList()).value
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(
        key1 = null,
        block = {
            mainViewModel.eventsUi.receiveAsFlow().collect {
                when (it) {
                    is MainViewModel.EventUi.NavigateToEventIdScreen -> {
                        navController.navigate(
                            navigateString(
                                NavigationRoute.ROUTE_EVENT_ID,
                                Pair(NavigationArguments.ARGUMENT_EVENT_ID, it.id)
                            )
                        )
                    }
                    is MainViewModel.EventUi.NavigateToEventScreen -> {
                        navController.navigate(
                            navigateString(
                                NavigationRoute.ROUTE_EVENT,
                                Pair(
                                    NavigationArguments.ARGUMENT_EVENT,
                                    Json.encodeToString(it.event)
                                )
                            )
                        )
                    }
                }
            }
        }
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.main_screen))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mainViewModel.sendAction(MainViewModel.Action.DeleteAllEvents)
            }) {
                Icon(Icons.Outlined.Delete, contentDescription = "delete button")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth()
        ) {

            ListTile(
                textId = stringResource(id = R.string.id),
                textEvent = stringResource(id = R.string.event),
                textTime = stringResource(id = R.string.time)
            ) {
                mainViewModel.sendAction(MainViewModel.Action.HeaderItemClicked)
            }

            LazyColumnSwipeToDismiss(
                events = events,
                lazyListState = lazyListState,
                onDelete = { id ->
                    mainViewModel.sendAction(MainViewModel.Action.DeleteEventById(id))
                },
                onClickToId = { id ->
                    mainViewModel.sendAction(MainViewModel.Action.IdItemClicked(id = id))
                },
                onClickToEvent = { event ->
                    mainViewModel.sendAction(MainViewModel.Action.EventItemClicked(event = event))
                },
            )
            ButtonTile(title = stringResource(id = R.string.button_add_event)) {
                mainViewModel.sendAction(MainViewModel.Action.ButtonAddEventClicked)
                coroutineScope.launch {
                    do {
                        lazyListState.scroll(MutatePriority.Default) {
                            scrollBy(40f)
                        }
                        delay(1L)
                    } while (true)
                }
            }
        }
    }
}