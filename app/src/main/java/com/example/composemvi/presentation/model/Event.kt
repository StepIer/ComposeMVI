package com.example.composemvi.presentation.model

import com.example.composemvi.domain.model.EventDomainModel
import java.time.LocalDateTime

data class Event(
    val id: Int? = null,
    val event: String,
    val time: LocalDateTime
)

fun Event.toDomainModel(): EventDomainModel {
    return EventDomainModel(
        id = this.id,
        event = this.event,
        time = this.time
    )
}

fun EventDomainModel.toPresentationModel(): Event {
    return Event(
        id = this.id,
        event = this.event,
        time = this.time
    )
}