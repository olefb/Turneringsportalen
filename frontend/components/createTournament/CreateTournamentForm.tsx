"use client";

import { Button, Flex, TextField } from "@radix-ui/themes";
import { useState } from "react";

export default function CreateTournamentForm() {
  const [inputFields, setInputFields] = useState({
    name: "",
    start_date: "",
    start_time: "",
    location: "",
    playing_fields: 0,
    time_between_matches: 0,
  });

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    setInputFields({ ...inputFields, [e.target.name]: e.target.value });
  }

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    console.log(inputFields);
  }

  const labelStyle = {
    fontWeight: "500",
    color: "var(--text-color)",
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{
        width: "100%",
        maxWidth: "400px",
        padding: "24px",
        border: "1px solid var(--border-color)",
        borderRadius: "28px",
        backgroundColor: "var(--form-background)",
        color: "var(--text-color)",
      }}
    >
      <Flex direction="column" gap="4">
        <div style={{ display: "flex", flexDirection: "column", gap: "16px" }}>
          <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
            <label style={labelStyle} htmlFor="name">
              Tournament Name
            </label>
            <input
              id="name"
              name="name"
              value={inputFields.name}
              onChange={handleChange}
              className="px-2 py-1 rounded border w-full"
            />
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
            <label style={labelStyle} htmlFor="start_date">
              Start Date
            </label>
            <input
              id="start_date"
              name="start_date"
              type="date"
              value={inputFields.start_date}
              onChange={handleChange}
              className="px-2 py-1 rounded border w-full"
            />
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
            <label style={labelStyle} htmlFor="start_time">
              Start Time
            </label>
            <input
              id="start_time"
              name="start_time"
              type="time"
              value={inputFields.start_time}
              onChange={handleChange}
              className="px-2 py-1 rounded border w-full"
            />
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
            <label style={labelStyle} htmlFor="location">
              Location
            </label>
            <input
              id="location"
              name="location"
              value={inputFields.location}
              onChange={handleChange}
              className="px-2 py-1 rounded border w-full"
            />
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
            <label style={labelStyle} htmlFor="playing_fields">
              Number of Playing Fields
            </label>
            <input
              id="playing_fields"
              name="playing_fields"
              type="number"
              value={inputFields.playing_fields}
              onChange={handleChange}
              className="px-2 py-1 rounded border w-full"
            />
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
            <label style={labelStyle} htmlFor="time_between_matches">
              Time Between Matches (minutes)
            </label>
            <input
              id="time_between_matches"
              name="time_between_matches"
              type="number"
              value={inputFields.time_between_matches}
              onChange={handleChange}
              className="px-2 py-1 rounded border w-full"
            />
          </div>
        </div>

        <Button style={{ width: "fit-content" }} type="submit">
          Create Tournament
        </Button>
      </Flex>
    </form>
  );
}
/**
  export type Tournament = {
  tournament_id: number;
  name: string;
  start_date: string;
  start_time: string;
  location: string;
  playing_fields: number;
  time_between_matches: number;
};
  */
