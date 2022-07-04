package com.example.composemvi.di

import android.content.Context
import androidx.room.Room
import com.example.composemvi.data.adapter.EventsRepositoryImpl
import com.example.composemvi.data.db.EventsDao
import com.example.composemvi.data.db.EventsDatabase
import com.example.composemvi.domain.adapter.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideEventsDao(database: EventsDatabase): EventsDao {
        return database.eventsDao()
    }

    @Provides
    @Singleton
    fun provideEventsDatabase(@ApplicationContext context: Context): EventsDatabase {
        return Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            "events"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEventsRepository(eventsDao: EventsDao): EventsRepository {
        return EventsRepositoryImpl(
            eventsDao = eventsDao
        )
    }
}

