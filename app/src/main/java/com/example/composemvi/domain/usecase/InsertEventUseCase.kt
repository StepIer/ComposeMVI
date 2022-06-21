package com.example.composemvi.domain.usecase

import com.example.composemvi.domain.model.EventDomainModel

interface InsertEventUseCase {
    suspend fun invoke(event: EventDomainModel)
}