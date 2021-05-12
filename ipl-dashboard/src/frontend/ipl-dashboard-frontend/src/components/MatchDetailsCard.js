import React from "react";
import { Link } from "react-router-dom";

export const MatchDetailsCard = ({ match, matchesOfYear, matchOfTeam }) => {
  console.log(matchOfTeam);
  //console.log(match);
  const oppositionTeam =
    matchOfTeam === match.team1 ? match.team2 : match.team1;
  const matchesOfTeamUrl = `/teams/${oppositionTeam}/matches/${matchesOfYear}`;
  return (
    <div className="MatchDetailsCard">
      <h4>
        Match Detail vs <Link to={matchesOfTeamUrl}>{oppositionTeam}</Link>{" "}
        (*Team 1 Bats First)
      </h4>

      <table id="customers">
        <tbody>
          <tr>
            <th>Match Date</th>
            <th>Team 1</th>
            <th>Team 2</th>
            <th>Toss Winner</th>
            <th>Match Winner</th>
            <th>Player of the Match</th>
            <th>Result</th>
            <th>City</th>
            <th>Venue</th>
            <th>Umpire 1</th>
            <th>Umpire 2</th>
            <th>Eliminator</th>
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
            <td>{match.city}</td>
            <td>{match.venue}</td>
            <td>{match.umpire1}</td>
            <td>{match.umpire2}</td>
            <td>{match.eliminator}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default MatchDetailsCard;
