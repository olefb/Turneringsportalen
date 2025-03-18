package com.turneringsportalen.backend.entities

import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Match(
        @SerialName("match_id") val matchId: Int,
        @SerialName("tournament_id") val tournamentId: Int,
        @SerialName("time") @Serializable(with = InstantIso8601Serializer::class) val time: Instant,
        @SerialName("game_location_id") val gameLocationId: Int
)
