package com.example.composemvi.presentation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvi.domain.usecase.DeleteEventUseCase
import com.example.composemvi.domain.usecase.DeleteEventsUseCase
import com.example.composemvi.domain.usecase.InsertEventUseCase
import com.example.composemvi.domain.usecase.LoadEventsUseCase
import com.example.composemvi.presentation.model.Event
import com.example.composemvi.presentation.model.toDomainModel
import com.example.composemvi.presentation.model.toPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deleteEventUseCase: DeleteEventUseCase,
    private val deleteEventsUseCase: DeleteEventsUseCase,
    private val insertEventUseCase: InsertEventUseCase,
    private val loadEventsUseCase: LoadEventsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val action = Channel<Action>(Channel.UNLIMITED)

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        handleAction()
        initEvents()
    }

    private fun initEvents() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                events = loadEventsUseCase.invoke().map { list ->
                    list.map {
                        it.toPresentationModel()
                    }
                }
            )
        }
    }

    fun sendAction(actionToSend: Action) {
        action.trySend(actionToSend)
    }

    private fun handleAction() {
        viewModelScope.launch {
            action.consumeAsFlow().collect() { action ->
                when (action) {
                    Action.ButtonAddEventClicked -> {
                        insertEventUseCase.invoke(
                            Event(
                                event = "ButtonAddEventClicked",
                                time = LocalDateTime.now()
                            ).toDomainModel()
                        )
                    }
                    is Action.DeleteEventById -> {
                        deleteEventUseCase.invoke(action.id)
                        insertEventUseCase.invoke(
                            Event(
                                event = "DeleteEventById by ${action.id} id",
                                time = LocalDateTime.now()
                            ).toDomainModel()
                        )
                    }
                    Action.DeleteAllEvents -> {
                        deleteEventsUseCase.invoke()
                        insertEventUseCase.invoke(
                            Event(
                                event = "DeleteAllEvents",
                                time = LocalDateTime.now()
                            ).toDomainModel()
                        )
                    }
                }
            }
        }
    }

    sealed class Action {
        object ButtonAddEventClicked : Action()
        data class DeleteEventById(val id: Int) : Action()
        object DeleteAllEvents : Action()
    }

    data class State(
        val events: Flow<List<Event>> = flowOf()
    )
}

//private fun handleAction() {
//    viewModelScope.launch {
//        action.consumeAsFlow().collect() { action ->
//            when (action) {
//                is Action.ButtonAddEventClicked -> {
//                    insertEventUseCase.invoke(Event(event = action.event, time = LocalDateTime.now()).toDomainModel())
//                }
//            }
//        }
//    }
//}
//
//sealed class Action {
//    data class ButtonAddEventClicked(
//        val event: String = "ButtonAddEventClicked"
//    ) : Action()
//}