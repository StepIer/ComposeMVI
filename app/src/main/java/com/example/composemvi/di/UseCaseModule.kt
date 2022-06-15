package com.example.composemvi.di

import com.example.composemvi.domain.adapter.EventsRepository
import com.example.composemvi.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideDeleteEventUseCase(eventsRepository: EventsRepository): DeleteEventUseCase {
        return DeleteEventUseCaseImpl(eventsRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteEventsUseCase(eventsRepository: EventsRepository): DeleteEventsUseCase {
        return DeleteEventsUseCaseImpl(eventsRepository)
    }

    @Provides
    @Singleton
    fun provideInsertEventUseCase(eventsRepository: EventsRepository): InsertEventUseCase {
        return InsertEventUseCaseImpl(eventsRepository)
    }

    @Provides
    @Singleton
    fun provideLoadEventsUseCase(eventsRepository: EventsRepository): LoadEventsUseCase {
        return LoadEventsUseCaseImpl(eventsRepository)
    }
}
