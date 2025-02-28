package com.turneringsportalen.backend.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tournament(
        @SerialName("tournament_id") val tournamentId : Int,
        val name : String,
        @SerialName("start_date") val startDate : Instant,
        val location : String,
        @SerialName("match_interval") val matchInterval : Int
)