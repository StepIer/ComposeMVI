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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                onClick = { id ->
                    mainViewModel.sendAction(MainViewModel.Action.EventItemClicked(id = id))
                }
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