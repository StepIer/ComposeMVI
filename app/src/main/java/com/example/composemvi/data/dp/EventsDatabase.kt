package com.example.composemvi.data.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EventDataModel::class], version = 1)
@TypeConverters(LocalDateTimeConverters::class)
abstract class EventsDatabase : RoomDatabase() {
        abstract fun eventsDao(): EventsDao
}