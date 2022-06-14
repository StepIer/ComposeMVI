package com.example.composemvi.data.adapter

import com.example.composemvi.data.dp.EventsDao
import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.flow.Flow

class EventsRepositoryImpl(
    private val eventsDao: EventsDao
) : EventsRepository {
    override fun loadEvents(): Flow<List<EventDomainModel>> {
        return eventsDao.getAllEvents()
    }

    override fun deleteEvents() {
        eventsDao.getAllEvents()
    }

    override fun deleteEvent(id: Int) {
        eventsDao.deleteEvent(id)
    }

    override fun addEvent(event: EventDomainModel) {
        eventsDao.insertEvent(event)
    }
}