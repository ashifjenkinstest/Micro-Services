import React from "react";
import { Link } from "react-router-dom";

import "../csss/BallByBallDetailsCard.scss";

export const BallByBallDetailsCard = ({
  ballByBallDetailedData,
  fInningRuns,
  fInningWickets,
  sInningRuns,
  sInningWickets,
}) => {
  //if (fInningRuns) console.log("fInningRun==>" + fInningRuns);
  //if (sInningRuns) console.log("sInningRun==>" + sInningRuns);
  const playerProfileRoute =
    process.env.REACT_APP_PLAYER_MATCH_PROFILE_ROUTE +
    ballByBallDetailedData.matchId +
    "/";
  return (
    <div className="BallByBallDetailsCard">
      <div className="score-section">
        <span>
          <h4>{ballByBallDetailedData.battingTeam}</h4>
          <h4>
            Inning: {ballByBallDetailedData.inning} -{" "}
            {ballByBallDetailedData.over}.{ballByBallDetailedData.ball}
          </h4>
          {ballByBallDetailedData.inning === 1 ? (
            <h4>
              ({fInningRuns}/{fInningWickets})
            </h4>
          ) : (
            <h4>
              ({sInningRuns}/{sInningWickets})
            </h4>
          )}
        </span>
      </div>
      <div className="bowl-detail-section">
        <span>
          {ballByBallDetailedData.isWicket ? (
            <h5>
              <Link to={playerProfileRoute + ballByBallDetailedData.bowler}>
                {ballByBallDetailedData.bowler}
              </Link>
              to{" "}
              <Link to={playerProfileRoute + ballByBallDetailedData.batsman}>
                {" "}
                {ballByBallDetailedData.batsman}{" "}
              </Link>
              (W)
            </h5>
          ) : (
            <h5>
              <Link to={playerProfileRoute + ballByBallDetailedData.bowler}>
                {ballByBallDetailedData.bowler}
              </Link>
              to{" "}
              <Link to={playerProfileRoute + ballByBallDetailedData.batsman}>
                {" "}
                {ballByBallDetailedData.batsman}
              </Link>
            </h5>
          )}

          {ballByBallDetailedData.extrasType !== "NA" ? (
            <h5>
              Extra : {ballByBallDetailedData.extrasType},{" "}
              {ballByBallDetailedData.extraRuns} Runs
            </h5>
          ) : (
            ""
          )}

          {ballByBallDetailedData.isWicket ? (
            <h5>
              <Link
                to={playerProfileRoute + ballByBallDetailedData.playerDismissed}
              >
                {ballByBallDetailedData.playerDismissed}
              </Link>
              Out, {ballByBallDetailedData.dismissalKind} by{" "}
              <Link to={playerProfileRoute + ballByBallDetailedData.fielder}>
                {ballByBallDetailedData.fielder}
              </Link>
            </h5>
          ) : (
            ""
          )}
          {ballByBallDetailedData.batsmanRuns === 4 ? (
            <h5>{ballByBallDetailedData.batsman} hits a Four</h5>
          ) : (
            ""
          )}
          {ballByBallDetailedData.batsmanRuns === 6 ? (
            <h5>{ballByBallDetailedData.batsman} hits a Six</h5>
          ) : (
            ""
          )}
          {ballByBallDetailedData.batsmanRuns === 0 &&
          !ballByBallDetailedData.isWicket &&
          ballByBallDetailedData.extrasType === "NA" ? (
            <h5>Dot Ball</h5>
          ) : (
            ""
          )}

          {ballByBallDetailedData.batsmanRuns !== 0 &&
          ballByBallDetailedData.batsmanRuns !== 4 &&
          ballByBallDetailedData.batsmanRuns !== 6 ? (
            <h5>{ballByBallDetailedData.batsmanRuns} run/s.</h5>
          ) : (
            ""
          )}
        </span>
      </div>
      <div className="over-highlight-section no-match-played-card">
        <span>
          <h3>
            {ballByBallDetailedData.extrasType !== "NA"
              ? ballByBallDetailedData.extraRuns + "(E)"
              : ""}
          </h3>
          <h3>{ballByBallDetailedData.isWicket ? "Out" : ""}</h3>
          <h3>
            {ballByBallDetailedData.isWicket &&
            ballByBallDetailedData.extrasType !== "NA"
              ? ballByBallDetailedData.batsmanRuns
              : ""}
          </h3>
          <h3>
            {ballByBallDetailedData.extrasType === "NA" &&
            ballByBallDetailedData.batsmanRuns === 0 &&
            !ballByBallDetailedData.isWicket
              ? "D"
              : ""}

            {ballByBallDetailedData.extrasType === "NA" &&
            ballByBallDetailedData.batsmanRuns !== 0 &&
            !ballByBallDetailedData.isWicket
              ? ballByBallDetailedData.batsmanRuns
              : ""}
          </h3>
        </span>
      </div>
    </div>
  );
};

export default BallByBallDetailsCard;
