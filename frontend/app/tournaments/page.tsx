import TournamentsOverview from "@/components/tournament/TournamentsOverview";
import React from "react";
import styles from "../../styles/tournaments/TournamentsPageStyle.module.css";

export default function TournamentsPage() {
    return (
        <div className={styles.container}>
            <h1>Upcomming tournaments</h1>

            <TournamentsOverview />
        </div>
    );
}