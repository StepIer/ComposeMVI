package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.flow.Flow

class LoadEventsUseCaseImpl(
    private val eventsRepository: EventsRepository
) : LoadEventsUseCase {
    override fun invoke(): Flow<List<EventDomainModel>> {
        return eventsRepository.loadEvents()
    }
}