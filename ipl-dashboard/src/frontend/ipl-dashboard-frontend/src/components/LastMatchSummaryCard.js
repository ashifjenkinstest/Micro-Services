import React from "react";
import { Link } from "react-router-dom";

import "../csss/LatestMatchSummaryCard.scss";

export const LastMatchSummary = ({ mainTeam, match }) => {
  const rootTeam = mainTeam === match.team1 ? match.team2 : match.team1;
  const rootTeamRoute = "/teams/" + rootTeam;

  const isMatchWonByMainTeam = mainTeam === match.winner;
  return (
    // <div className="LastestMatchSummaryCard">
    <div
      className={
        isMatchWonByMainTeam
          ? "LastestMatchSummaryCard won-card"
          : "LastestMatchSummaryCard lost-card"
      }
    >
      <div className="">
        <h5>Latest Match vs</h5>
        <h3>
          <Link to={rootTeamRoute}>{rootTeam}</Link>
        </h3>
        <h5 className="match-date">Played On {match.matchDate}</h5>
        <h5 className="match-date">
          at {match.venue},{match.city}
        </h5>
      </div>
      <div className="latest-match-additional-detail">
        <span>
          <h4>First Inning</h4>
          <p>{match.team1}</p>
          <h4>Second Inning</h4>
          <p>{match.team2}</p>
          <h4>Toss</h4>
          <p className="toss-winner-descision">
            {match.tossWinner} won the TOSS and elected to {match.tossDecision}{" "}
            first
          </p>
        </span>
        <h4>Match Winner:</h4>
        <p className="match-winner-result-margin">
          {match.winner} by {match.resultMargin} {match.result}
        </p>
        <h5 className="match-umpires">Umpires :</h5>
        <p>
          {match.umpire1}, {match.umpire2}
        </p>
      </div>
    </div>
  );
};

export default LastMatchSummary;
