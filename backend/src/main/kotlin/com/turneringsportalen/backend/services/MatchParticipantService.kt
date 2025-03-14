package com.turneringsportalen.backend.services

import com.turneringsportalen.backend.entities.Match
import com.turneringsportalen.backend.entities.MatchParticipant
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.PathVariable

class MatchParticipantService(private val client: SupabaseClient) {

    suspend fun findMatchParticipantById(id: Int): MatchParticipant{
        return client.from("matchparticipant").select {
            filter{
                eq("participant_id", id)
            }
        }.decodeSingle()
    }

    suspend fun addMatchParticipant(matchParticipant: MatchParticipant){
        client.from("participant").insert(matchParticipant)

    }

    suspend fun deleteMatchParticipant(id: Int){
        client.from("participant").delete{
            filter{
                eq("participant_id", id)
            }
        }
    }

    suspend fun updateMatchParticipants(matchId: Int, participantId: Int, index: Int){
        client.from("matchparticipant").update(
            {
            set("match_id", matchId)
            set("participant_id", participantId)
            set("index", index)
        }
        )
    }
}