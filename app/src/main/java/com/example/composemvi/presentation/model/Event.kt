package com.example.composemvi.presentation.model

import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@Serializable
data class Event(
    val id: Int? = null,
    val event: String = "",
    val time: Long = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
)

fun Event.toDomainModel(): EventDomainModel {
    return EventDomainModel(
        id = this.id,
        event = this.event,
        time = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.time), ZoneId.systemDefault())
    )
}

fun EventDomainModel.toPresentationModel(): Event {
    return Event(
        id = this.id,
        event = this.event,
        time = this.time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )
}
