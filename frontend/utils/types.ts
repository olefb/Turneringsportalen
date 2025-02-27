export type Tournament = {
  tournamentId: number;
  name: string;
  startDate: Date;
  location: string;
  fields: TournamentField[];
  matchInterval: number;
};

export type TournamentField = {
  fieldId: number;
  tournamentId: number;
  fieldName: string;
};

export type Participant = {
  participantId: number;
  name: string;
  tournamentId: number;
};

export type Match = {
  matchId: number;
  time: Date;
  gameLocationId: number;
  tournamentId: number;
};

export type MatchParticipant = {
  matchId: number;
  participantId: number;
  index: number;
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

export type TournamentNoID = {
  name: string;
  startDate: Date;
  location: string;
  fields: TournamentField[];
  matchInterval: number;
};

export type TournamentField = {
  fieldId: number;
  tournamentId: number;
  fieldName: string;
};
