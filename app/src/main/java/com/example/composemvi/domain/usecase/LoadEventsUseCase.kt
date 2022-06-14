package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.model.EventDomainModel
import kotlinx.coroutines.flow.Flow

interface LoadEventsUseCase {
    fun invoke(): Flow<List<EventDomainModel>>
}