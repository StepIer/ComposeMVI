package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository

class DeleteEventUseCaseImpl(
    private val eventsRepository: EventsRepository
) : DeleteEventUseCase {
    override fun invoke(id: Int) {
        eventsRepository.deleteEvent(id)
    }
}