package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteEventsUseCaseImpl(
    private val eventsRepository: EventsRepository
) : DeleteEventsUseCase {
    override suspend fun invoke() {
        withContext(Dispatchers.IO) {
            eventsRepository.deleteEvents()
        }
    }
}