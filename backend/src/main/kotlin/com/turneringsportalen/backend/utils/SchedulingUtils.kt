package com.turneringsportalen.backend.utils

import com.turneringsportalen.backend.dto.MatchParticipantWithParticipantDTO
import com.turneringsportalen.backend.dto.MatchWithParticipantsDTO
import com.turneringsportalen.backend.entities.Participant
import com.turneringsportalen.backend.entities.Tournament
import com.turneringsportalen.backend.entities.TournamentField
import kotlinx.datetime.Clock

fun createGroups(participants: List<Participant>, minMatches: Int) : List<List<Participant>> {
    val groupSize = minMatches + 1
    val groups = mutableListOf<List<Participant>>()

    var i = 0
    while (i < participants.size) {
        val remaining = participants.size - i

        // If remaining participants are more than one full group but not enough for two full groups, merge them all into one
        if (remaining in (groupSize + 1) until (2 * groupSize)) {
            groups.add(participants.subList(i, participants.size)) // One final large group
            break
        } else {
            groups.add(participants.subList(i, minOf(i + groupSize, participants.size)))
            i += groupSize
        }
    }

    return groups
}

fun scheduleStandardGroups(group: List<Participant>, minimumMatches: Int, tournament: Tournament, fields: List<TournamentField>) : List<MatchWithParticipantsDTO> {
    // Implementation
    val groupSize = minimumMatches + 1
    var matchid = 0;
    val matches = mutableListOf<MatchWithParticipantsDTO>()

    for (i in group.indices) {
        for (j in i + 1 until groupSize) {
            val match = MatchWithParticipantsDTO(
                matchId = matchid,
                tournamentID = tournament.tournamentId ?: -1,
                time = Clock.System.now(),
                gameLocationId = fields[0].fieldId ?: -1,
                participants = listOf(
                    MatchParticipantWithParticipantDTO(
                        matchId = matchid, participant = group[i], index = 1
                    ), MatchParticipantWithParticipantDTO(
                        matchId = matchid, participant = group[j], index = 2
                    )
                )
            )
            matchid++
            matches.add(match)
        }
    }

    return matches
}

// Exception when group has more members than normal group size
fun scheduleExceptionGroups(group: List<Participant>, minimumMatches: Int, groupSize: Int, tournament: Tournament, fields: List<TournamentField>) : List<MatchWithParticipantsDTO> {
    var matchid = 0;
    var matches = mutableListOf<MatchWithParticipantsDTO>()

    // Track min matches
    val matchCount = mutableMapOf<Int, Int>()  // Track match count per participant
    // sets each participant on each participantId in the group to 0
    group.forEach { matchCount[it.participantId ?: Int.MAX_VALUE] = 0 }

    val matchSplitter = Math.ceil(group.size.toDouble() / 2).toInt()
    val isEven = group.size % 2 == 0;

    if(isEven && (minimumMatches * 2) == groupSize) {
        // case when there is an even number of participants, and the group can be split in half
        matches = schedule2TimesMinimumMatches(group, minimumMatches, tournament, fields)
    }
    else if(isEven) {
        // case when there is an even number of group members, but
        matches = scheduleOtherEvenGroups(group, minimumMatches, tournament, fields)
    }
    else if(groupSize > (minimumMatches * 2)) {
        // case when group has more than double the minimum matches worth of participants
        matches = scheduleOddGroupHigherThan2TimesMinimumMatches(group, minimumMatches, tournament, fields)
    }
    else {
        // case when group has less than double the minimum matches and an odd number of participants
        matches = scheduleOddGroupsLowerThan2TimesMinimumMatches(group, minimumMatches, tournament, fields)
    }

    return matches;
}

/*
For inspiration
if (!isEven) {

    println("Final Count")
    for (count in matchCount) {
        println(count)
    }
    println()
 */

private fun schedule2TimesMinimumMatches(group: List<Participant>, minimumMatches: Int, tournament: Tournament, fields: List<TournamentField>) : MutableList<MatchWithParticipantsDTO> {
    val matches = mutableListOf<MatchWithParticipantsDTO>()
    var matchid = 0;

    // Track min matches
    val matchCount = mutableMapOf<Int, Int>()  // Track match count per participant
    // sets each participant on each participantId in the group to 0
    group.forEach { matchCount[it.participantId ?: Int.MAX_VALUE] = 0 }

    val matchSplitter = Math.ceil(group.size.toDouble() / 2).toInt()
    for (i in group.indices) {
        val participantI = group[i]

        for (j in matchSplitter until minOf(group.size, matchSplitter*2)) {
            val participantJ = group[j]

            // Ensure participant J and I has less than 3 matches before continuing
            if ((matchCount[participantJ.participantId]
                    ?: 0) != minimumMatches && (matchCount[participantI.participantId] ?: 0) != minimumMatches
            ) {
                val match = MatchWithParticipantsDTO(
                    matchId = matchid,
                    tournamentID = tournament.tournamentId ?: -1,
                    time = Clock.System.now(),
                    gameLocationId = fields[0].fieldId ?: -1,
                    participants = listOf(
                        MatchParticipantWithParticipantDTO(
                            matchId = matchid,
                            participant = participantI,
                            index = 1
                        ),
                        MatchParticipantWithParticipantDTO(
                            matchId = matchid,
                            participant = participantJ,
                            index = 2
                        )
                    )
                )
                matchid++
                matches.add(match)

                // Increment match count for both participants
                matchCount[participantI.participantId ?: Int.MAX_VALUE] =
                    (matchCount[participantI.participantId] ?: 0) + 1
                matchCount[participantJ.participantId ?: Int.MAX_VALUE] =
                    (matchCount[participantJ.participantId] ?: 0) + 1

                for (count in matchCount) {
                    println(count)
                }
                println()
            }
        }
    }
    return matches
}

private fun scheduleOtherEvenGroups(group: List<Participant>, minimumMatches: Int, tournament: Tournament, fields: List<TournamentField>) : MutableList<MatchWithParticipantsDTO> {
    val matches = mutableListOf<MatchWithParticipantsDTO>()
    var matchid = 0;

    // Track min matches
    val matchCount = mutableMapOf<Int, Int>()  // Track match count per participant
    // sets each participant on each participantId in the group to 0
    group.forEach { matchCount[it.participantId ?: Int.MAX_VALUE] = 0 }

    return mutableListOf()
}

private fun scheduleOddGroupHigherThan2TimesMinimumMatches(group: List<Participant>, minimumMatches: Int, tournament: Tournament, fields: List<TournamentField>) : MutableList<MatchWithParticipantsDTO> {
    val matches = mutableListOf<MatchWithParticipantsDTO>()
    var matchid = 0;

    // Track min matches
    val matchCount = mutableMapOf<Int, Int>()  // Track match count per participant
    // sets each participant on each participantId in the group to 0
    group.forEach { matchCount[it.participantId ?: Int.MAX_VALUE] = 0 }

    return mutableListOf()
}

private fun scheduleOddGroupsLowerThan2TimesMinimumMatches(group: List<Participant>, minimumMatches: Int, tournament: Tournament, fields: List<TournamentField>) : MutableList<MatchWithParticipantsDTO> {
    val matches = mutableListOf<MatchWithParticipantsDTO>()
    var matchid = 0;

    // Track min matches
    val matchCount = mutableMapOf<Int, Int>()  // Track match count per participant
    // sets each participant on each participantId in the group to 0
    group.forEach { matchCount[it.participantId ?: Int.MAX_VALUE] = 0 }

    val matchSplitter = Math.ceil(group.size.toDouble() / 2).toInt()
    for (i in group.indices) {
        val participantI = group[i]

        for (j in matchSplitter until minOf(group.size, matchSplitter*2)) {
            val participantJ = group[j]

            // Ensure participant J and I has less than 3 matches before continuing
            if ((matchCount[participantJ.participantId]
                    ?: 0) != minimumMatches && (matchCount[participantI.participantId] ?: 0) != minimumMatches
            ) {
                val match = MatchWithParticipantsDTO(
                    matchId = matchid,
                    tournamentID = tournament.tournamentId ?: -1,
                    time = Clock.System.now(),
                    gameLocationId = fields[0].fieldId ?: -1,
                    participants = listOf(
                        MatchParticipantWithParticipantDTO(
                            matchId = matchid,
                            participant = participantI,
                            index = 1
                        ),
                        MatchParticipantWithParticipantDTO(
                            matchId = matchid,
                            participant = participantJ,
                            index = 2
                        )
                    )
                )
                matchid++
                matches.add(match)

                // Increment match count for both participants
                matchCount[participantI.participantId ?: Int.MAX_VALUE] =
                    (matchCount[participantI.participantId] ?: 0) + 1
                matchCount[participantJ.participantId ?: Int.MAX_VALUE] =
                    (matchCount[participantJ.participantId] ?: 0) + 1

                for (count in matchCount) {
                    println(count)
                }
                println()
            }
        }
    }

    // Loop through the first `matchSplitter` participants and pair them with the next available one
    for (i in 0 until matchSplitter - 1 step 2) { // Pair all except the last one
        val participantI = group[i]
        val participantJ = group[i + 1]

        // Ensure both participants have less than 3 matches before scheduling
        if ((matchCount[participantI.participantId] ?: 0) < 3 && (matchCount[participantJ.participantId] ?: 0) < 3) {
            val match = MatchWithParticipantsDTO(
                matchId = matchid,
                tournamentID = tournament.tournamentId ?: -1,
                time = Clock.System.now(),
                gameLocationId = fields[0].fieldId ?: -1,
                participants = listOf(
                    MatchParticipantWithParticipantDTO(
                        matchId = matchid, participant = participantI, index = 1
                    ),
                    MatchParticipantWithParticipantDTO(
                        matchId = matchid, participant = participantJ, index = 2
                    )
                )
            )
            matchid++
            matches.add(match)

            // Increment match count for both participants
            matchCount[participantI.participantId ?: Int.MAX_VALUE] =
                (matchCount[participantI.participantId] ?: 0) + 1
            matchCount[participantJ.participantId ?: Int.MAX_VALUE] =
                (matchCount[participantJ.participantId] ?: 0) + 1
        }
    }

    val lastParticipant = group[matchSplitter - 1]
    val firstParticipant = group[0]

    val match = MatchWithParticipantsDTO(
        matchId = matchid,
        tournamentID = tournament.tournamentId ?: -1,
        time = Clock.System.now(),
        gameLocationId = fields[0].fieldId ?: -1,
        participants = listOf(
            MatchParticipantWithParticipantDTO(
                matchId = matchid, participant = lastParticipant, index = 1
            ),
            MatchParticipantWithParticipantDTO(
                matchId = matchid, participant = firstParticipant, index = 2
            )
        )
    )
    matchid++
    matches.add(match)

    // Increment match count for both participants
    matchCount[lastParticipant.participantId ?: Int.MAX_VALUE] =
        (matchCount[lastParticipant.participantId] ?: 0) + 1
    matchCount[firstParticipant.participantId ?: Int.MAX_VALUE] =
        (matchCount[firstParticipant.participantId] ?: 0) + 1

    println("?")

    return matches
}