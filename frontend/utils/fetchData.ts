/**
 * This file contains the functions to fetch data from the server
 */

const API_URL = "http://localhost:8080";

/**
 * Method that fetches the list of tournaments from the server
 * @returns The list of tournaments
 */
export async function fetchTournaments() {
  const response = await fetch(`${API_URL}/tournaments`);
  const data = await response.json();
  return data;
}

/**
 * Method that fetches a single tournament from the server by its id
 * @param id The id of the tournament to fetch
 * @returns The tournament with the given id
 */
export async function fetchTournamentById(id: number) {
  const response = await fetch(`${API_URL}/tournaments/${id}`);
  const data = await response.json();
  return data;
}
