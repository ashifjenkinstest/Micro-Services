import React, { useState, useEffect } from "react";
import TeamImage from "../components/TeamImage";
import TeamPage from "./TeamPage";
import "../csss/AllTeams.scss";

function AllTeams() {
  const [data, setData] = useState({ teams: [] });

  useEffect(async () => {
    const response = await fetch("http://localhost:8099/teams");

    const data = await response.json();
    //console.log("teams" + data);
    setData(data);
  }, []);

  if (!data || data.teams.length === 0)
    return (
      <div>
        <h1>IPL Dashboard</h1>
        <ul>No Team Found</ul>
      </div>
    );
  return (
    <div className="AllTeams">
      <div className="ipl-teams-section">
        <h1>IPL Teams</h1>
      </div>
      <div className="team-name-section">
        {data.teams.map((team) => (
          <h3 className="team-name-section" key={team.id}>
            {team.teamName}
          </h3>
        ))}
      </div>
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default AllTeams;