package com.example.composemvi.data.dp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<EventDataModel>>

    @Query("DELETE FROM events")
    fun deleteAllEvents()

    @Query("DELETE FROM events WHERE id = :id")
    fun deleteEvent(id: Int)

    @Insert
    fun insertEvent(event: EventDataModel)
}