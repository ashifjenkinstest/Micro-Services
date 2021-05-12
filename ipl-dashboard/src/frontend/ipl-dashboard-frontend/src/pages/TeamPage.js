import React, { useState, useEffect } from "react";
import MatchSummaryCard from "../components/MatchSummaryCard";
import TeamImage from "../components/TeamImage";
import { useParams, Link } from "react-router-dom";
import "../csss/TeamPage.scss";
import LastMatchSummary from "../components/LastMatchSummaryCard";

export const TeamPage = ({ team }) => {
  const [teamLocal, setTeamLocal] = useState({ lastestMatches: [] });

  const { rootTeamName } = useParams();

  const tname = typeof team === "undefined" ? "Rajasthan Royals" : team;

  useEffect(() => {
    const fetchAllTeams = async () => {
      //console.log(team);

      console.log("tname " + tname);
      const response = await fetch(
        // "http://localhost:8099/teams/" + team
        `http://localhost:8099/teams/${rootTeamName}`
      );

      const data = await response.json();
      // console.log(data.lastestMatches.map(dd => console.log(dd)));

      setTeamLocal(data);

      //console.log(data);
    };
    fetchAllTeams();
  }, [rootTeamName]);
  const matchesWon = teamLocal.totalWins;
  const matchesLost = teamLocal.totalMatches - teamLocal.totalWins;
  if (!teamLocal || !teamLocal.teamName) {
    return <h1>Team Not Found!</h1>;
  }
  return (
    <React.Fragment>
      <div className="TeamPage">
        <div className="team-name-section">
          <h1 className="team-name">Team {teamLocal.teamName}</h1>
          <TeamImage team={teamLocal.teamName} />
        </div>
        <div className="win-loss-section">
          Win/LossesMatches {teamLocal.totalWins}/{matchesLost}
        </div>
        <div className="last-match-summary-section">
          <LastMatchSummary
            key={teamLocal.lastestMatches[0].id}
            match={teamLocal.lastestMatches[0]}
            mainTeam={rootTeamName}
          />
        </div>

        {teamLocal.lastestMatches.slice(1).map((latestMatch) => (
          <div className="match-summary-section">
            <MatchSummaryCard
              key={latestMatch.id}
              match={latestMatch}
              mainTeam={rootTeamName}
            />
          </div>
        ))}

        <div>
          <a href="#">More</a>
        </div>
      </div>
    </React.Fragment>
  );
};

export default TeamPage;
