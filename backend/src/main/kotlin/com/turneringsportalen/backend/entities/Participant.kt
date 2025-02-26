package com.turneringsportalen.backend.entities

import kotlinx.serialization.Serializable

@Serializable
data class Participant(
        val participantId: Int,
<<<<<<< Updated upstream
        val name: String,
        val participatesIn: Tournament
=======
        val tournamentId: Int,
        val name: String
>>>>>>> Stashed changes
)