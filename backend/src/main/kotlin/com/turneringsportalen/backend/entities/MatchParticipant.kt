package com.turneringsportalen.backend.entities

import kotlinx.serialization.Serializable

@Serializable
data class MatchParticipant (
    val matchId: Int,
    val participantId : Int,
    val index: Int
)