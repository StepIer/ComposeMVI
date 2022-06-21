package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LoadEventsUseCaseImpl(
    private val eventsRepository: EventsRepository
) : LoadEventsUseCase {
    override suspend fun invoke(): Flow<List<EventDomainModel>> = withContext(Dispatchers.IO) {
        return@withContext eventsRepository.loadEvents()
    }
}