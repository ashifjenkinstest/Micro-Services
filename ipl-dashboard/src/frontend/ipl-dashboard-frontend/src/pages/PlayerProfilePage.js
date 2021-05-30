import React, { useEffect, useState } from "react";
import TeamImage from "../components/TeamImage";
import { useParams } from "react-router-dom";
import "../csss/PlayerProfilePage.scss";

export const PlayerProfilePage = () => {
  const [playerStats, setPlayerStats] = useState({ playerProfile: [] });
  const { matchId } = useParams();
  const { playerName } = useParams();

  useEffect(() => {
    const fetchPlayerProfile = async () => {
      const response = await fetch(
        // "http://localhost:8099/teams/" + team
        `${process.env.REACT_APP_API_ROOT_URL}/match/ipl/players/${matchId}/${playerName}`
      );

      const data = await response.json();

      setPlayerStats(data);
    };
    fetchPlayerProfile();
  }, [matchId, playerName]);
  console.log(playerStats.playerProfile);
  return (
    <div className="PlayerProfilePage">
      <div className="prfile">
        <TeamImage team="PLAYER" />
        <div className="container-section">
          <h2>
            <b>{playerName} </b>
          </h2>
          <p>Runs: {playerStats.playerProfile.runs}</p>
          <p>Wickets: {playerStats.playerProfile.wickets}</p>
          <p>Catches: {playerStats.playerProfile.catches}</p>
          <p>Run outs: {playerStats.playerProfile.runOuts}</p>
          <p>Stumps: {playerStats.playerProfile.stumps}</p>
          <p>Player of the Match: {playerStats.playerProfile.mom} </p>
        </div>
      </div>
      <div className="matchscore">
        <h1>CURRENT MATCH SCORE SECTION</h1>
      </div>
      <div className="careerscore">
        <h1>CAREER MATCHES SCORE SECTION</h1>
      </div>
    </div>
  );
};

export default PlayerProfilePage;
