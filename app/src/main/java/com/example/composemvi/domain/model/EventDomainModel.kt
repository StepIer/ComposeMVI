package com.example.composemvi.domain.model

import java.time.LocalDateTime

data class EventDomainModel(
    val id: Int?,
    val event: String,
    val time: LocalDateTime
)
