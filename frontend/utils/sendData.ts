/**
 * This file contains the functions to send data to the server
 */
"use server"; // This file will run on the server side, as server actions

import { TournamentNoID } from "./types";

const API_URL = "http://localhost:8080";

/**
 * Function to send a POST request to the server to create a tournament
 * @param data The tournament object being created
 *
 */
export async function createTournament(data: TournamentNoID) {
  const response = await fetch(`${API_URL}/tournaments`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error("Failed to create tournament");
  }
}
