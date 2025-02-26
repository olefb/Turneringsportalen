package com.turneringsportalen.backend.entities

import kotlinx.serialization.Serializable

@Serializable
data class Participant(
        val participantId: Int,
        val tournamentId: Int,
        val name: String
)