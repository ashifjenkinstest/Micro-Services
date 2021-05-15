import React, { useState, useEffect } from "react";
import TeamImage from "../components/TeamImage";
import TeamPage from "./TeamPage";
import "../csss/AllTeams.scss";
import { Link } from "react-router-dom";
import { PieChart } from "react-minimal-pie-chart";

function AllTeams() {
  const [data, setData] = useState({ teams: [] });

  const rootTeamRoute = "";
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
      <div className="ipl-teams-section ipl-teams-section-card">
        <div className="ipl-name">
          <h1>IPL Teams</h1>
        </div>
      </div>
      <div className="team-image">
        <TeamImage team="IPL" />
      </div>

      {data.teams.map((team) => (
        <div className="all-teams-section stat-teams-section-card">
          <div className="team-name-section">
            <Link to={"teams/" + rootTeamRoute + team.teamName + "/statistics"}>
              {team.teamName}
            </Link>

            <h4> Played / Won / Lost</h4>
            <p>
              {team.totalMatches} / {team.totalWins} /{" "}
              {team.totalMatches - team.totalWins}
            </p>
          </div>
          <div>
            <div className="pie-chart-section">
              <PieChart
                data={[
                  {
                    title: "Won",
                    value: team.totalWins,
                    color: "#4da375",
                  },
                  {
                    title: "Lost",
                    value: team.totalMatches - team.totalWins,
                    color: "#a34d5d",
                  },
                ]}
              />
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default AllTeams;
