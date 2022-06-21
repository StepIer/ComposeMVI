package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteEventUseCaseImpl(
    private val eventsRepository: EventsRepository
) : DeleteEventUseCase {
    override suspend fun invoke(id: Int) {
        withContext(Dispatchers.IO) {
            eventsRepository.deleteEvent(id)
        }
    }
}