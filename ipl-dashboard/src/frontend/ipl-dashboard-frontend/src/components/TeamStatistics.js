import React, { useState, useEffect } from "react";
import "../csss/TeamStatistics.scss";
import { useParams, Link } from "react-router-dom";
import { PieChart } from "react-minimal-pie-chart";
import TeamImage from "./TeamImage";
export const TeamStatistics = ({ team }) => {
  const state = {
    count: 0,
    imageUrl: "https://picsum.photos/200",
    tags: ["tag1", "tag2", "tag3"],
  };
  const { rootTeamName } = useParams();

  const [stat, setStat] = useState({ statistics: [] });

  useEffect(() => {
    const fetchTeamStatistics = async () => {
      //console.log(team);

      console.log("tname " + rootTeamName);
      const response = await fetch(
        // "http://localhost:8099/teams/" + team
        `http://localhost:8099/teams/${rootTeamName}/statistics`
      );

      const data = await response.json();
      // console.log(data.lastestMatches.map(dd => console.log(dd)));

      setStat(data);

      console.log(data);
    };
    fetchTeamStatistics();
  }, []);

  return (
    <React.Fragment>
      <div className="TeamStatistics">
        <div className="team-name-section">
          <h1 className="team-name"> {rootTeamName}</h1>
        </div>
        <div className="team-image">
          <TeamImage team={rootTeamName} />
        </div>
        {stat.statistics.map((st) => (
          <div className="win-vs-loss-section stat-teams-section-card">
            <div className="win-loss-count-section">
              <h4>vs {st.opponent}</h4>

              <Link to={"teams/" + st.opponent}></Link>
              <h4>Win</h4>
              <p>{st.won}</p>
              <h4>Loss</h4>
              <p>{st.lost}</p>
            </div>
            <div>
              <div className="pie-chart-section">
                <PieChart
                  data={[
                    {
                      title: "Won",
                      value: st.won,
                      color: "#4da375",
                    },
                    { title: "Lost", value: st.lost, color: "#a34d5d" },
                  ]}
                />
              </div>
            </div>
          </div>
        ))}
      </div>
    </React.Fragment>
  );
};

export default TeamStatistics;
