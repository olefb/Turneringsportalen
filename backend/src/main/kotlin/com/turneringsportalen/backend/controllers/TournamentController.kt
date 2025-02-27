package com.turneringsportalen.backend.controllers

import com.turneringsportalen.backend.entities.Tournament
import com.turneringsportalen.backend.services.TournamentService
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tournaments")
class TournamentController(private val service: TournamentService) {

    @GetMapping("/")
    fun findAllTournaments() = runBlocking { service.findAllTournaments() }

    @GetMapping("/{id}")
    fun findTournamentById(@RequestParam id: Int) = runBlocking { service.findById(id) }

    @PostMapping("/")
    fun addNewTournament(@RequestBody tournament: Tournament) = runBlocking { service.createTournament(tournament) }

    @PutMapping("/{id}")
    fun updateTournament(@RequestParam id: Int, @RequestBody tournament: Tournament) = runBlocking {
        service.update(
            tournament.tournamentId, // Ensure the `update` function matches the expected types
            tournament.name,
            tournament.startDate,
            tournament.location,
            tournament.matchInterval
        )
    }

    @DeleteMapping("/{id}")
    fun deleteTournament(@RequestParam id: Int) = runBlocking { service.deleteTournament(id) }
}