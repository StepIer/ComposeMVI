package com.example.composemvi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composemvi.domain.model.EventDomainModel
import java.time.LocalDateTime

@Entity(tableName = "events")
data class EventDataModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "event")
    val event: String,
    @ColumnInfo(name = "time")
    val time: LocalDateTime
)

fun EventDataModel.toDomainModel(): EventDomainModel {
    return EventDomainModel(
        id = this.id,
        event = this.event,
        time = this.time
    )
}

fun EventDomainModel.toDataModel(): EventDataModel {
    return EventDataModel(
        id = this.id,
        event = this.event,
        time = this.time
    )
}