import React, { useState, useEffect } from "react";
import TeamImage from "../components/TeamImage";
import TeamPage from "./TeamPage";

function AllTeams() {
  const [data, setData] = useState({ teams: [] });
  const [data2, setData2] = useState({ hits: [] });

  useEffect(async () => {
    const response = await fetch("http://localhost:8099/teams");

    const data = await response.json();
    console.log("teams" + data);
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
    <div>
      <h1>IPL Dashboard</h1>
      <ul>
        {data.teams.map((team) => (
          <li key={team.id}>
            <TeamPage key={team.teamName} team={team.teamName} />
          </li>
        ))}
      </ul>
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default AllTeams;
