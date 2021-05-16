import React, { useState, useEffect } from "react";
import MatchDetailsCard from "../components/MatchDetailsCard";
import TeamImage from "../components/TeamImage";
import TeamPage from "./TeamPage";
import "../csss/MatchPage.scss";
import { useParams, Link } from "react-router-dom";
import YearSelector from "../components/YearSelector";

function MatchPage() {
  const [matches, setMatches] = useState({ match: [] });

  const { rootTeamName } = useParams();
  const { matchYear } = useParams();
  const matchesOfTeam = rootTeamName;
  const matchFromYear = matchYear;

  useEffect(async () => {
    const response = await fetch(
      `http://localhost:8099/teams/${rootTeamName}/matches?year=${matchYear}`
    );
    const data = await response.json();
    setMatches(data);
    console.log(matches);
  }, [rootTeamName, matchYear]);

  if (!matches || matches.length === 0)
    return (
      <div>
        <h1>IPL Dashboard</h1>
        <ul>No Team Found</ul>
      </div>
    );
  return (
    <div className="MatchPage">
      <div className="latest-match-from-year">
        <h3>Latest Matches of </h3>
        <h1></h1>
        <h1>{matchesOfTeam} </h1>
        <h3>since </h3> <h1> {matchFromYear} </h1>
        <Link to={`/teams`}> All Teams</Link>
      </div>
      <div className="team-image">
        <TeamImage team={matchesOfTeam} />
      </div>

      <div className="year-selector-section">
        <YearSelector team={matchesOfTeam} />
      </div>
      <div className="match-detail-section">
        {matches.match.map((mat) => (
          <div className="match-detail-card-section">
            <MatchDetailsCard
              key={mat.id}
              match={mat}
              matchesOfYear={matchYear}
              matchOfTeam={matchesOfTeam}
            />
          </div>
        ))}
      </div>
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default MatchPage;
