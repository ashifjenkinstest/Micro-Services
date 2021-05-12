import React, { useState, useEffect } from "react";
import MatchDetailsCard from "../components/MatchDetailsCard";
import TeamImage from "../components/TeamImage";
import TeamPage from "./TeamPage";
import { useParams, Link } from "react-router-dom";

function MatchPage() {
  const [matches, setMatches] = useState({ match: [] });
  const [data2, setData2] = useState({ hits: [] });

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
  }, [rootTeamName]);

  if (!matches || matches.length === 0)
    return (
      <div>
        <h1>IPL Dashboard</h1>
        <ul>No Team Found</ul>
      </div>
    );
  return (
    <div>
      <h1>
        Latest Matches of {matchesOfTeam} from Year {matchFromYear}{" "}
      </h1>
      <TeamImage team={matchesOfTeam} />
      <ul>
        {matches.match.map((mat) => (
          <MatchDetailsCard
            key={mat.id}
            match={mat}
            matchesOfYear={matchYear}
            matchOfTeam={matchesOfTeam}
          />
        ))}
      </ul>
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default MatchPage;
