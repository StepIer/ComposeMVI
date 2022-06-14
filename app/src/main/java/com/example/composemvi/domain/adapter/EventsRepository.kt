package com.example.composemvi.domain.adapter

import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.flow.Flow

interface EventsRepository {

    fun loadEvents(): Flow<List<EventDomainModel>>
    fun deleteEvents()
    fun deleteEvent(id: Int)
    fun addEvent(event: EventDomainModel)
}