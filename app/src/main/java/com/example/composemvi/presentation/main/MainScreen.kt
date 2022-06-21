package com.example.composemvi.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composemvi.R
import com.example.composemvi.presentation.main.components.ButtonTile
import com.example.composemvi.presentation.main.components.LazyColumnSwipeToDismiss
import com.example.composemvi.presentation.model.Event

@Composable
fun MainScreen() {

    val mainViewModel: MainViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val state = mainViewModel.state.collectAsState()
    val events: List<Event> = state.value.events.collectAsState(emptyList()).value

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
                .fillMaxSize()

        ) {
//            LazyColumn {
//                items(events) {
//                    EventTile(event = it) {
//
//                    }
//                }
//                item {
//                    ButtonTile(title = stringResource(id = R.string.button_add_event)) {
//                        mainViewModel.sendAction(MainViewModel.Action.ButtonAddEventClicked)
//                    }
//                }
//            }

            LazyColumnSwipeToDismiss(
                events = events,
                onDelete = { id ->
                    mainViewModel.sendAction(MainViewModel.Action.DeleteEventById(id))
                },
                content = {
                    item {
                        ButtonTile(title = stringResource(id = R.string.button_add_event)) {
                            mainViewModel.sendAction(MainViewModel.Action.ButtonAddEventClicked)
                        }
                    }
                }
            )
        }
    }
}