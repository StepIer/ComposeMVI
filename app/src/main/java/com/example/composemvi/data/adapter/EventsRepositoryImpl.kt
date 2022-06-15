package com.example.composemvi.data.adapter

import com.example.composemvi.data.db.EventsDao
import com.example.composemvi.data.model.toDataModel
import com.example.composemvi.data.model.toDomainModel
import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventsRepositoryImpl(
    private val eventsDao: EventsDao
) : EventsRepository {
    override fun loadEvents(): Flow<List<EventDomainModel>> {
        return eventsDao.getAllEvents().map { list -> list.map { it.toDomainModel() } }
    }

    override fun deleteEvents() {
        eventsDao.getAllEvents()
    }

    override fun deleteEvent(id: Int) {
        eventsDao.deleteEvent(id)
    }

    override fun addEvent(event: EventDomainModel) {
        eventsDao.insertEvent(event.toDataModel())
    }
}