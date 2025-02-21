export type Tournament = {
  tournament_id: number;
  name: string;
  start_date: string;
  start_time: string;
  location: string;
  playing_fields: number;
  time_between_matches: number;
};

export type Participant = {
  participant_id: number;
  name: string;
  tournament_id: number;
};

export type Match = {
  match_id: number;
  participant1: number;
  participant2: number;
  time: string;
  date: Date;
  game_location: string;
  tournament_id: number;
};

export type MatchWithParticipantsAndTournament = Match & {
  participant1: Participant;
  participant2: Participant;
  tournament: Tournament;
};

export type TournamentWithParticipantsAndMatches = Tournament & {
  participants: Participant[];
  matches: Match[];
};
