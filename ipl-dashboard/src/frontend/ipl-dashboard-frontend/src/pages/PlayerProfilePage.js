import React, { useEffect, useState } from "react";
import TeamImage from "../components/TeamImage";
import { Link, useParams } from "react-router-dom";
import "../csss/PlayerProfilePage.scss";

export const PlayerProfilePage = () => {
  const [playerStats, setPlayerStats] = useState({
    playerProfile: [],
    matchScoreAndSummary: [],
  });
  const { matchId } = useParams();
  const { playerName } = useParams();

  const rootTeamRoute = process.env.REACT_APP_TEAM_ROUTE;

  useEffect(() => {
    const fetchPlayerProfile = async () => {
      const response = await fetch(
        // "http://localhost:8099/teams/" + team
        `${process.env.REACT_APP_API_ROOT_URL}/match/ipl/players/${matchId}/${playerName}`
      );
      const data = await response.json();
      setPlayerStats(data);
    };
    fetchPlayerProfile();
  }, [matchId, playerName]);

  console.log(
    "playerStats.matchScoreAndSummary " + playerStats.matchScoreAndSummary
  );
  console.log("playerStats.playerProfile " + playerStats.playerProfile);
  console.log(
    "playerStats.matchScoreAndSummary.matchInningsScore " +
      playerStats.matchScoreAndSummary.matchInningsScore
  );
  if (
    !playerStats.playerProfile ||
    playerStats.playerProfile.length === 0 ||
    playerStats.matchScoreAndSummary.match.id === 0
  ) {
    return (
      <div>
        <div className="pnf-section">
          <h1>Oops... Page Not Found!</h1>
        </div>
      </div>
    );
  }

  return (
    <React.Fragment>
      <div className="PlayerProfilePage">
        <div className="profile">
          <TeamImage team="PLAYER" />
          <div className="container-section">
            <h2>
              <b>{playerName} </b>
            </h2>
            <p>
              <b>
                Runs: {playerStats.playerProfile.runs} , ( 6:{" "}
                {playerStats.playerProfile.sixes} , 4:{" "}
                {playerStats.playerProfile.fours} , 30+:{" "}
                {playerStats.playerProfile.thirtyPlusRunsInMatch}, 50+:{" "}
                {playerStats.playerProfile.fiftyPlusRunsInMatch}, 100+:{" "}
                {playerStats.playerProfile.hundredPlusRunsInMatch} ){" "}
              </b>
            </p>
            <p>
              {" "}
              <b>
                Wickets: {playerStats.playerProfile.wickets} (
                {playerStats.playerProfile.bestBowlingFigureWickets}/
                {playerStats.playerProfile.bestBowlingFigureRuns}){" "}
              </b>
            </p>
            <p>
              <b>Catches: {playerStats.playerProfile.catches}</b>
            </p>
            <p>
              <b>Run outs: {playerStats.playerProfile.runOuts}</b>
            </p>
            <p>
              <b>Stumps: {playerStats.playerProfile.stumps}</b>
            </p>
            <p>
              <b>Player of the Match: {playerStats.playerProfile.mom} </b>
            </p>
          </div>
        </div>
        <div className="matchdetail">
          <div>
            <h3>
              {playerStats.matchScoreAndSummary.match.tossWinner} elected to{" "}
              {playerStats.matchScoreAndSummary.match.tossDecision} first
            </h3>
            <h2>
              <Link
                to={
                  rootTeamRoute +
                  playerStats.matchScoreAndSummary.matchInningsScore
                    .firstInningTeam
                }
              >
                {
                  playerStats.matchScoreAndSummary.matchInningsScore
                    .firstInningTeam
                }
              </Link>{" "}
              {
                playerStats.matchScoreAndSummary.matchInningsScore
                  .firstInningRuns
              }
              /
              {
                playerStats.matchScoreAndSummary.matchInningsScore
                  .firstInningWickets
              }{" "}
              (
              {
                playerStats.matchScoreAndSummary.matchInningsScore
                  .firstInningOvers
              }
              +)
            </h2>
            <h2>
              <Link
                to={
                  rootTeamRoute +
                  playerStats.matchScoreAndSummary.matchInningsScore
                    .secondInningTeam
                }
              >
                {
                  playerStats.matchScoreAndSummary.matchInningsScore
                    .secondInningTeam
                }
              </Link>{" "}
              {
                playerStats.matchScoreAndSummary.matchInningsScore
                  .secondInningRuns
              }
              /
              {
                playerStats.matchScoreAndSummary.matchInningsScore
                  .secondInningWickets
              }{" "}
              (
              {
                playerStats.matchScoreAndSummary.matchInningsScore
                  .secondInningOvers
              }
              +)
            </h2>
            <h4>
              {playerStats.matchScoreAndSummary.match.winner} Won by{" "}
              {playerStats.matchScoreAndSummary.match.resultMargin}{" "}
              {playerStats.matchScoreAndSummary.match.result}
            </h4>
            <h3>PLAYER OF THE MATCH</h3>
            <h5>{playerStats.matchScoreAndSummary.match.playerOfMatch}</h5>
          </div>
        </div>

        <div className="matchscore"></div>
        <div className="careerscore"></div>
      </div>
    </React.Fragment>
  );
};

export default PlayerProfilePage;
