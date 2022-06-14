package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel

class InsertEventUseCaseImpl(
    private val eventsRepository: EventsRepository
) : InsertEventUseCase {
    override fun invoke(event: EventDomainModel) {
        eventsRepository.addEvent(event)
    }
}