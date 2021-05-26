import React from "react";
import "../csss/MatchSummaryCard.scss";
import { Link } from "react-router-dom";

export const MatchSummaryCard = ({ mainTeam, match }) => {
  const rootTeam = mainTeam === match.team1 ? match.team2 : match.team1;
  const rootTeamRoute = "/teams/" + rootTeam;
  const matchDetailsRoute = "/teams/matches/" + match.id;
  const isMatchWonByMainTeam = mainTeam === match.winner;

  return (
    <div
      className={
        isMatchWonByMainTeam
          ? "MatchSummaryCard won-card"
          : "MatchSummaryCard lost-card"
      }
    >
      <span>vs</span>
      <h3>
        <Link to={rootTeamRoute}>{rootTeam}</Link>
      </h3>
      <h5 className="match-date">Played On {match.matchDate}</h5>

      <span>
        <h4 className="toss-winner-descision">
          {match.tossWinner} won the toss and elected to {match.tossDecision}{" "}
          first.
        </h4>
      </span>
      <h2 className="match-winner-result-margin">
        {match.winner} by {match.resultMargin} {match.result}
      </h2>
      <h4 className="player-of-the-match">
        {match.playerOfMatch} was Player of the Match
      </h4>
      <h6>
        <Link to={matchDetailsRoute}>More Details</Link>
      </h6>
    </div>
  );
};

export default MatchSummaryCard;
