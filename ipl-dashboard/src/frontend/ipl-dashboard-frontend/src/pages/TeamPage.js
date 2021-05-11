import React, { useState, useEffect } from "react";
import MatchSummaryCard from "../components/MatchSummaryCard";
import TeamImage from "../components/TeamImage";
import { useParams, Link } from "react-router-dom";

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

  if (!teamLocal || !teamLocal.teamName) {
    return <h1>Team Not Found!</h1>;
  }
  return (
    <React.Fragment>
      <div className="TeamPage">
        <div className="card">
          <div className="container">
            <h1>
              <b>Team {teamLocal.teamName}</b>
            </h1>
            <TeamImage team={teamLocal.teamName} />
            <p>
              {teamLocal.teamName} ,Matches Played : {teamLocal.totalMatches}{" "}
              ,Matches Won: {teamLocal.totalWins}
            </p>
          </div>
        </div>
        <ul>
          {teamLocal.lastestMatches.slice(1).map((latestMatch) => (
            <MatchSummaryCard
              key={latestMatch.id}
              match={latestMatch}
              mainTeam={rootTeamName}
            />
          ))}
        </ul>
      </div>
    </React.Fragment>
  );
};

export default TeamPage;
