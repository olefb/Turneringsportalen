package com.turneringsportalen.backend.services

import com.turneringsportalen.backend.entities.Match
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.datetime.Instant

class MatchService(private val client: SupabaseClient) {

    suspend fun findAllMatches(): List<Match> {
        return client.from("match").select().decodeList<Match>()
    }

    suspend fun findById(id: Int): Match? {
        return client.from("match").select {
            filter{
                eq("match_id", id)
            }
        }.decodeSingle()
    }

    suspend fun createMatch(match: Match){
        client.from("match").insert(match)
    }

    suspend fun deleteMatch(id: Int){
        client.from("match").delete{
            filter {
                eq("match_id",id)
            }
        }

    }

    suspend fun update(matchId: Int, tournamentId: Int, time: Instant, gameLocationId: Int){
        client.from("matches").update(
            {
                set("match_id", matchId)
                set("tournamentId", tournamentId)
                set("time", time)
                set("gameLocationId", gameLocationId)
            }
        ){
            filter {
                eq("matchId", matchId)
            }
        }
    }
}
