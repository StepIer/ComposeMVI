package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertEventUseCaseImpl(
    private val eventsRepository: EventsRepository
) : InsertEventUseCase {
    override suspend fun invoke(event: EventDomainModel) {
        withContext(Dispatchers.IO) {
            eventsRepository.addEvent(event)
        }
    }
}