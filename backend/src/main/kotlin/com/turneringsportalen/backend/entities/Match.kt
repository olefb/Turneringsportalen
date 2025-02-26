package com.turneringsportalen.backend.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Match(
        val matchId : Int,
        val tournamentID : Int,
        val time : Instant,
        val gameLocationId : Int
)