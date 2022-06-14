package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository

class DeleteEventsUseCaseImpl(
    private val eventsRepository: EventsRepository
) : DeleteEventsUseCase {
    override fun invoke() {
        eventsRepository.deleteEvents()
    }
}