package com.example.composemvi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composemvi.data.model.EventDataModel

@Database(entities = [EventDataModel::class], version = 1)
@TypeConverters(LocalDateTimeConverters::class)
abstract class EventsDatabase : RoomDatabase() {
        abstract fun eventsDao(): EventsDao
}