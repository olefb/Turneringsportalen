package com.turneringsportalen.backend.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Tournament(
        val tournamentId: Int,
        val name : String,
        val startDate : Instant,
        val location : String,
<<<<<<< Updated upstream
        val fields : List<String>, // May need to be changed based on how supabase interprets this
        val matchInterval : String
=======
        val matchInterval : Int
>>>>>>> Stashed changes
)