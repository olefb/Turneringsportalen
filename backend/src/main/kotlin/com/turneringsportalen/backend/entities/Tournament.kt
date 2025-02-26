package com.turneringsportalen.backend.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Tournament(
        val tournamentId : Int,
        val name : String,
        val startDate : Instant,
        val location : String,
        val matchInterval : Int
)