import React, { useState, useEffect } from "react";
import TeamImage from "../components/TeamImage";
import "../csss/AllTeams.scss";
import { Link } from "react-router-dom";
import { PieChart } from "react-minimal-pie-chart";

function AllTeams() {
  const [data, setData] = useState({ teams: [] });

  const rootTeamRoute = "teams/";

  useEffect(() => {
    const fetchAllTeams = async () => {
      const response = await fetch(
        `${process.env.REACT_APP_API_ROOT_URL}/teams`
      );

      const data = await response.json();
      setData(data);
    };
    fetchAllTeams();
  }, [rootTeamRoute]);

  if (!data || data.teams.length === 0)
    return (
      <div className="AllTeams">
        <div className="ipl-teams-section ipl-teams-section-card">
          <div className="ipl-name">
            <h3>No IPL Teams Found</h3>
          </div>

          <div className="team-image">
            <TeamImage team="IPL" />
          </div>
        </div>
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
        <div
          className="all-teams-section stat-teams-section-card"
          key={team.teamName}
        >
          <div className="team-name-section">
            <div className="link-section">
              <Link to={rootTeamRoute + team.teamName + "/statistics"}>
                {team.teamName}
              </Link>
            </div>
            <h6> Played / Won / Lost</h6>
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
              <div className="link-section">
                <Link to={rootTeamRoute + team.teamName + ""}>
                  Latest Matches >
                </Link>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default AllTeams;
