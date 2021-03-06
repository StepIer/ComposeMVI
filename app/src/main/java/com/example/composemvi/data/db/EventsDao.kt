package com.example.composemvi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composemvi.data.model.EventDataModel
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