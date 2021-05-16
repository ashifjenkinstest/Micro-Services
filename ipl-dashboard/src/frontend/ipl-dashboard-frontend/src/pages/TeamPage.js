import React, { useState, useEffect } from "react";

import TeamImage from "../components/TeamImage";
import { useParams, Link } from "react-router-dom";
import "../csss/TeamPage.scss";
import LastMatchSummary from "../components/LastMatchSummaryCard";
import MatchSummaryCard from "../components/MatchSummaryCard";
import "../csss/MatchSummaryCard.scss";
import { PieChart } from "react-minimal-pie-chart";

export const TeamPage = ({ team }) => {
  const [teamLocal, setTeamLocal] = useState({ lastestMatches: [] });

  const { rootTeamName } = useParams();

  const tname = rootTeamName;
  const moreRoute =
    rootTeamName + "/matches/" + process.env.REACT_APP_IPL_LAST_YEAR;
  const statsRoute = rootTeamName + "/statistics";

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
  }, [rootTeamName, moreRoute, statsRoute]);
  const matchesWon = teamLocal.totalWins;
  const matchesLost = teamLocal.totalMatches - teamLocal.totalWins;
  if (!teamLocal || !teamLocal.teamName) {
    return <h1>Team Not Found!</h1>;
  }
  return (
    <React.Fragment>
      <div className="TeamPage">
        <div className="team-name-section">
          <h1 className="team-name"> {teamLocal.teamName}</h1>
        </div>
        <div className="team-image">
          <TeamImage team={teamLocal.teamName} />
        </div>

        <div className="win-loss-section">
          <h5>
            Win/Loss : {teamLocal.totalWins}/{matchesLost}
          </h5>
          <PieChart
            data={[
              {
                title: "Won",
                value: teamLocal.totalWins,
                color: "#4da375",
              },
              { title: "Lost", value: matchesLost, color: "#a34d5d" },
            ]}
          />
          <div className="more-section">
            <Link to={statsRoute}>Statistics ></Link>
          </div>
        </div>

        <div className="last-match-summary-section">
          <LastMatchSummary
            key={teamLocal.lastestMatches[0].id}
            match={teamLocal.lastestMatches[0]}
            mainTeam={rootTeamName}
          />
        </div>
        <div className="match-summary-vs summary-card">
          <h5></h5>
          <h2>Match Summary of previous matches</h2>
          <h5></h5>
        </div>

        {teamLocal.lastestMatches.slice(1).map((latestMatch) => (
          <div>
            <MatchSummaryCard
              className="match-summary-section"
              key={latestMatch.id}
              match={latestMatch}
              mainTeam={rootTeamName}
            />
          </div>
        ))}

        <div className="more-section">
          <Link to={moreRoute}>More ></Link>
        </div>
      </div>
    </React.Fragment>
  );
};

export default TeamPage;
