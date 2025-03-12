package com.turneringsportalen.backend.utils

import com.turneringsportalen.backend.entities.Participant
import com.turneringsportalen.backend.entities.Tournament
import com.turneringsportalen.backend.entities.TournamentField
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class SchedulingUtilsTest {

    private val tournament: Tournament = Tournament(1, "Test Tournament", Clock.System.now(), "Test Location", 30)
    private val tournamentId = tournament.tournamentId ?: 0 // Ensures all participants use the actual tournament ID

    private val potentialParticipants = listOf(
        Participant(1, tournamentId, "Trane gul"),
        Participant(2, tournamentId, "Trane grønn"),
        Participant(3, tournamentId, "Trane rød"),
        Participant(4, tournamentId, "Trane svart"),
        Participant(5, tournamentId, "Varegg gul"),
        Participant(6, tournamentId, "Varegg blå"),
        Participant(7, tournamentId, "Varegg lilla"),
        Participant(8, tournamentId, "Varegg svart"),
        Participant(9, tournamentId, "Varegg hvit"),
        Participant(10, tournamentId, "Nordnes gul"),
        Participant(11, tournamentId, "Nordnes svart"),
        Participant(12, tournamentId, "Baune svart"),
        Participant(13, tournamentId, "Nymark svart"),
        Participant(14, tournamentId, "Nymark hvit"),
        Participant(15, tournamentId, "Nymark rød"),
        Participant(16, tournamentId, "Nymark grå"),
        Participant(17, tournamentId, "Gneist hvit"),
        Participant(18, tournamentId, "Gneist blå"),
        Participant(19, tournamentId, "Gneist rød"),
        Participant(20, tournamentId, "Brann hvit"),
        Participant(21, tournamentId, "Brann rød"),
        Participant(22, tournamentId, "Brann svart"),
    )

    private val potentialFields = listOf(
        TournamentField(1, tournamentId, "Field A"),
        TournamentField(2, tournamentId, "Field B"),
        TournamentField(3, tournamentId, "Field C"),
        TournamentField(4, tournamentId, "Field D")
    )

    @Test
    fun testGeneratingTwoGroupsOfFour() {
        // Get the first 8 participants
        val participants = potentialParticipants.take(8)
        val minMatches = 3

        // Generate groups
        val groups = createGroups(participants, minMatches);

        // Ensure two groups was created
        assertEquals(2, groups.size, "Should have exactly 2 groups")

        // Ensure that both groups have 4 participants
        for (group in groups) {
            assertEquals(4, group.size, "Each group should have exactly 4 participants")
        }
    }

    @Test
    fun testGeneratingOneGroupOfFourAndOneGroupOfFive() {
        // Get the first 9 participants
        val participants = potentialParticipants.take(9)
        val minMatches = 3

        // Generate groups
        val groups = createGroups(participants, minMatches)

        // Ensure two groups was created
        assertEquals(2, groups.size, "Should have exactly 2 groups")

        // Ensure the first group has 4 participants
        assertEquals(4, groups[0].size, "The first group should have exactly 4 participants")

        // Ensure the second group has 5 participants
        assertEquals(5, groups[1].size, "The second group should have exactly 5 participants")
    }


    @Test
    fun testGeneratingTwoGroupsOfFourAndOneGroupOfSeven() {
        // Get the first 15 participants
        val participants = potentialParticipants.take(15)
        val minMatches = 3

        // Generate groups
        val groups = createGroups(participants, minMatches);

        // Ensure three groups was created
        assertEquals(3, groups.size, "Should have exactly 3 groups")

        // Ensure the first two groups have 4 participants
        assertEquals(4, groups[0].size, "The first group should have exactly 4 participants")
        assertEquals(4, groups[1].size, "The second group should have exactly 4 participants")

        // Ensure the final group has 7 participants
        assertEquals(7, groups[2].size, "The third group should have exactly 7 participants")
    }

    @Test
    fun testGeneratingGroupsOfFive() {
        val participants = potentialParticipants
        val minMatches = 4

        // Generate groups
        val groups = createGroups(participants, minMatches);

        // Ensure four groups was created
        assertEquals(4, groups.size, "Should have exactly 4 groups")

        // Ensure the first three groups have 5 participants
        assertEquals(5, groups[0].size, "The first group should have exactly 5 participants")
        assertEquals(5, groups[1].size, "The second group should have exactly 5 participants")
        assertEquals(5, groups[2].size, "The third group should have exactly 5 participants")

        // Ensure the final group has 7 participants
        assertEquals(7, groups[3].size, "The fourth group should have exactly 7 participants")
    }

    @Test
    fun testGeneratingGroupsOfSeven() {
        val participants = potentialParticipants
        val minMatches = 6

        // Generate groups
        val groups = createGroups(participants, minMatches);

        // Ensure three groups was created
        assertEquals(3, groups.size, "Should have exactly 3 groups")

        // Ensure the first two groups have 7 participants
        assertEquals(7, groups[0].size, "The first group should have exactly 7 participants")
        assertEquals(7, groups[1].size, "The second group should have exactly 7 participants")

        // Ensure the last group have 8 participants
        assertEquals(8, groups[2].size, "The third group should have exactly 8 participants")
    }

    @Test
    fun testScheduleStandardGroupWith3MinimumMatches() {
        // Select 4 participants
        val group = potentialParticipants.take(4)
        val minimumMatches = 3

        val matches = scheduleStandardGroups(group, minimumMatches, tournament, potentialFields)

        // Count matches per participant
        val matchCounts = mutableMapOf<Int, Int>() // Maps participant ID to the number of matches played
        for (match in matches) {
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }

        // Assert that each participant has played exactly 3 matches
        for (participant in group) {
            val count = matchCounts[participant.participantId] ?: 0
            assertEquals(3, count, "Participant ${participant.participantId} should have exactly 3 matches")
        }

        val playedMatches = mutableSetOf<Pair<Int, Int>>() // Stores unique matchups

        for (match in matches) {
            val participantIds = match.participants.map { it.participant.participantId ?: 0 }

            // Ensure each match has exactly two participants
            assertEquals(2, participantIds.size, "Each match should have exactly two participants")

            val (id1, id2) = participantIds.sorted() // Normalize order
            val matchPair = id1 to id2

            // Check if the match pair already exists
            assertFalse(playedMatches.contains(matchPair), "Duplicate match found between $id1 and $id2")

            // Add match pair to the set
            playedMatches.add(matchPair)

            // Update match counts
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }
    }

    @Test
    fun testScheduleStandardGroupWith4MinimumMatches() {
        // Select 4 participants
        val group = potentialParticipants.take(5)
        val minimumMatches = 4

        val matches = scheduleStandardGroups(group, minimumMatches, tournament, potentialFields)

        // Count matches per participant
        val matchCounts = mutableMapOf<Int, Int>() // Maps participant ID to the number of matches played
        for (match in matches) {
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }

        // Assert that each participant has played exactly 3 matches
        for (participant in group) {
            val count = matchCounts[participant.participantId] ?: 0
            assertEquals(4, count, "Participant ${participant.participantId} should have exactly 4 matches")
        }

        val playedMatches = mutableSetOf<Pair<Int, Int>>() // Stores unique matchups

        for (match in matches) {
            val participantIds = match.participants.map { it.participant.participantId ?: 0 }

            // Ensure each match has exactly two participants
            assertEquals(2, participantIds.size, "Each match should have exactly two participants")

            val (id1, id2) = participantIds.sorted() // Normalize order
            val matchPair = id1 to id2

            // Check if the match pair already exists
            assertFalse(playedMatches.contains(matchPair), "Duplicate match found between $id1 and $id2")

            // Add match pair to the set
            playedMatches.add(matchPair)

            // Update match counts
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }
    }

    @Test
    fun testScheduleStandardGroupWith7MinimumMatches() {
        // Select 4 participants
        val group = potentialParticipants.take(8)
        val minimumMatches = 7

        val matches = scheduleStandardGroups(group, minimumMatches, tournament, potentialFields)

        // Count matches per participant
        val matchCounts = mutableMapOf<Int, Int>() // Maps participant ID to the number of matches played
        for (match in matches) {
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }

        // Assert that each participant has played exactly 3 matches
        for (participant in group) {
            val count = matchCounts[participant.participantId] ?: 0
            assertEquals(7, count, "Participant ${participant.participantId} should have exactly 7 matches")
        }

        val playedMatches = mutableSetOf<Pair<Int, Int>>() // Stores unique matchups

        for (match in matches) {
            val participantIds = match.participants.map { it.participant.participantId ?: 0 }

            // Ensure each match has exactly two participants
            assertEquals(2, participantIds.size, "Each match should have exactly two participants")

            val (id1, id2) = participantIds.sorted() // Normalize order
            val matchPair = id1 to id2

            // Check if the match pair already exists
            assertFalse(playedMatches.contains(matchPair), "Duplicate match found between $id1 and $id2")

            // Add match pair to the set
            playedMatches.add(matchPair)

            // Update match counts
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }
    }

    @Test
    fun testScheduleExceptionGroupWith3MinimumMatchesAnd5GroupSize() {
        // Select 4 participants
        val group = potentialParticipants.take(5)
        val minimumMatches = 3

        val matches = scheduleExceptionGroups(group, minimumMatches, group.size, tournament, potentialFields)

        // Count matches per participant
        val matchCounts = mutableMapOf<Int, Int>() // Maps participant ID to the number of matches played
        for (match in matches) {
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }

        // Ensure one participant has exactly 4 matches while the rest have 3
        var foundFourMatches = false

        for (participant in group) {
            val count = matchCounts[participant.participantId] ?: 0

            if (count == 4) {
                assertFalse(foundFourMatches, "More than one participant has 4 matches!")
                foundFourMatches = true
            } else {
                assertEquals(3, count, "Participant ${participant.participantId} should have exactly 3 matches")
            }
        }

        val playedMatches = mutableSetOf<Pair<Int, Int>>() // Stores unique matchups

        for (match in matches) {
            val participantIds = match.participants.map { it.participant.participantId ?: 0 }

            // Ensure each match has exactly two participants
            assertEquals(2, participantIds.size, "Each match should have exactly two participants")

            val (id1, id2) = participantIds.sorted() // Normalize order
            val matchPair = id1 to id2

            // Check if the match pair already exists
            assertFalse(playedMatches.contains(matchPair), "Duplicate match found between $id1 and $id2")

            // Add match pair to the set
            playedMatches.add(matchPair)

            // Update match counts
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }
    }

    @Test
    fun testScheduleExceptionGroupWith3MinimumMatchesAnd6GroupSize() {
        // Select 4 participants
        val group = potentialParticipants.take(6)
        val minimumMatches = 3

        val matches = scheduleExceptionGroups(group, minimumMatches, group.size, tournament, potentialFields)

        // Count matches per participant
        val matchCounts = mutableMapOf<Int, Int>() // Maps participant ID to the number of matches played
        for (match in matches) {
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }

        // Assert that each participant has played exactly 3 matches
        for (participant in group) {
            val count = matchCounts[participant.participantId] ?: 0
            assertEquals(3, count, "Participant ${participant.participantId} should have exactly 3 matches")
        }

        val playedMatches = mutableSetOf<Pair<Int, Int>>() // Stores unique matchups

        for (match in matches) {
            val participantIds = match.participants.map { it.participant.participantId ?: 0 }

            // Ensure each match has exactly two participants
            assertEquals(2, participantIds.size, "Each match should have exactly two participants")

            val (id1, id2) = participantIds.sorted() // Normalize order
            val matchPair = id1 to id2

            // Check if the match pair already exists
            assertFalse(playedMatches.contains(matchPair), "Duplicate match found between $id1 and $id2")

            // Add match pair to the set
            playedMatches.add(matchPair)

            // Update match counts
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }
    }

    @Test
    fun testScheduleExceptionGroupWith3MinimumMatchesAnd7GroupSize() {
        // Select 4 participants
        val group = potentialParticipants.take(7)
        val minimumMatches = 3

        val matches = scheduleExceptionGroups(group, minimumMatches, group.size, tournament, potentialFields)

        // Count matches per participant
        val matchCounts = mutableMapOf<Int, Int>() // Maps participant ID to the number of matches played
        for (match in matches) {
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }

        // Ensure one participant has exactly 4 matches while the rest have 3
        var foundFourMatches = false

        for (participant in group) {
            val count = matchCounts[participant.participantId] ?: 0

            if (count == 4) {
                assertFalse(foundFourMatches, "More than one participant has 4 matches!")
                foundFourMatches = true
            } else {
                assertEquals(3, count, "Participant ${participant.participantId} should have exactly 3 matches")
            }
        }

        val playedMatches = mutableSetOf<Pair<Int, Int>>() // Stores unique matchups

        for (match in matches) {
            val participantIds = match.participants.map { it.participant.participantId ?: 0 }

            // Ensure each match has exactly two participants
            assertEquals(2, participantIds.size, "Each match should have exactly two participants")

            val (id1, id2) = participantIds.sorted() // Normalize order
            val matchPair = id1 to id2

            // Check if the match pair already exists
            assertFalse(playedMatches.contains(matchPair), "Duplicate match found between $id1 and $id2")

            // Add match pair to the set
            playedMatches.add(matchPair)

            // Update match counts
            for (participant in match.participants) {
                matchCounts[participant.participant.participantId ?: 0] =
                    matchCounts.getOrDefault(participant.participant.participantId, 0) + 1
            }
        }
    }

}
