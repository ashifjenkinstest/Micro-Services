import React from "react";
import "../csss/MatchSummaryCard.css";
import { useParams, Link } from "react-router-dom";

export const MatchSummaryCard = ({ mainTeam, match }) => {
  const rootTeam = mainTeam === match.team1 ? match.team2 : match.team1;
  const rootTeamRoute = "/teams/" + rootTeam;
  return (
    <div className="MatchSummaryCard">
      <h5>Match Summary vs</h5>
      <h3>
        <Link to={rootTeamRoute}>{rootTeam}</Link>
      </h3>
      <table id="customers">
        <tbody>
          <tr>
            <th>Match Date</th>
            <th>Team</th>
            <th>Team</th>
            <th>Toss Winner</th>
            <th>Match Winner</th>
            <th>Player of the Match</th>

            <th>Result</th>
          </tr>
          <tr>
            <td>{match.matchDate}</td>
            <td>{match.team1}</td>
            <td>{match.team2}</td>
            <td>{match.tossWinner}</td>

            <td>{match.winner}</td>
            <td>{match.playerOfMatch}</td>
            <td>
              Won By {match.resultMargin} {match.result}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default MatchSummaryCard;
