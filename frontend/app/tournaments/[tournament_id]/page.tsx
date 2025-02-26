import React from 'react';
import TournamentView from '@/components/tournament/TournamentView';


export default function TournamentPage() {
  return (
    <div>
      <h1>Tournament Details</h1>
      <p>TEST DATA:</p>
      <p>TODO: Fetch</p>
      <TournamentView
        tournament={{
          tournament_id: 1,
          name: "Åsane Cup 2025",
          start_date: "2025-06-01",
          start_time: "12:00",
          location: "Åsane Arena",
          playing_fields: 3,
          time_between_matches: 15,
          participants: [
            { participant_id: 1, name: "Minde G14", tournament_id: 1 },
            { participant_id: 2, name: "Laksevåg G14", tournament_id: 1 },
            { participant_id: 3, name: "Åsane G14", tournament_id: 1 },
          ],
          matches: [
            {
              match_id: 1,
              participant1: 1,
              participant2: 2,
              time: "12:00",
              date: new Date(),
              game_location: "Football Field N",
              tournament_id: 1,
            },
            {
              match_id: 2,
              participant1: 2,
              participant2: 3,
              time: "12:15",
              date: new Date(),
              game_location: "Football Field S",
              tournament_id: 1,
            },
            {
              match_id: 3,
              participant1: 3,
              participant2: 1,
              time: "12:30",
              date: new Date(),
              game_location: "Multipurpose Field",
              tournament_id: 1,
            },
          ],
        }}
      />
    </div>
  );
}
