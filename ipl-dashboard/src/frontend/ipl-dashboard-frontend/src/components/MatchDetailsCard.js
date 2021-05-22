import React from "react";
import { Link } from "react-router-dom";
import "../csss/MatchDetailsCard.scss";
export const MatchDetailsCard = ({ match, matchesOfYear, matchOfTeam }) => {
  //console.log(match);
  const oppositionTeam =
    matchOfTeam === match.team1 ? match.team2 : match.team1;
  const matchesOfTeamUrl = `/teams/${oppositionTeam}/matches/${matchesOfYear}`;

  //console.log(match);
  const isMatchWonByMainTeam = matchOfTeam === match.winner;

  return (
    <div
      className={
        isMatchWonByMainTeam
          ? "MatchDetailsCard won-card"
          : "MatchDetailsCard lost-card"
      }
    >
      <h1>
        <span className="vs">vs</span>{" "}
        <Link to={matchesOfTeamUrl}>{oppositionTeam}</Link>
      </h1>
      <h5 className="match-date">Played On {match.matchDate}</h5>

      <span>
        <h4 className="toss-winner-descision">
          {match.tossWinner} won the TOSS and elected to {match.tossDecision}{" "}
          first
        </h4>
      </span>
      <h2 className="match-winner-result-margin">
        {match.winner} by {match.resultMargin} {match.result}
      </h2>
      <h4 className="player-of-the-match">
        {match.playerOfMatch} was Player of the Match
      </h4>
      <h5 className="match-venue">
        Venue :{match.venue},{match.city}
      </h5>
      <h6 className="match-umpires">
        Umpires : {match.umpire1}, {match.umpire2}
      </h6>
    </div>
  );
};

export default MatchDetailsCard;
