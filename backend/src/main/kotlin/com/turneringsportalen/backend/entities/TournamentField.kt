package com.turneringsportalen.backend.entities

import kotlinx.serialization.Serializable

@Serializable
data class TournamentField (
    val fieldId : Int,
    val tournamentId : Int,
    val fieldName : String
)