package com.example.composemvi.domain.usecase

interface DeleteEventUseCase {
    suspend fun invoke(id: Int)
}