import React from "react";
import { Link } from "react-router-dom";

export const BallByBallDetailsCard = ({ ballByBallDetailedData }) => {
  return (
    <div>
      <p>
        {ballByBallDetailedData.matchId}
        {ballByBallDetailedData.inning}
        {ballByBallDetailedData.over}
        {ballByBallDetailedData.ball}
        {ballByBallDetailedData.batsman}
        {ballByBallDetailedData.nonStriker}
        {ballByBallDetailedData.bowler}
        {ballByBallDetailedData.batsmanRuns}
        {ballByBallDetailedData.extraRuns}
        {ballByBallDetailedData.totalRuns}
        {ballByBallDetailedData.nonBoundary}
        {ballByBallDetailedData.isWicket}
        {ballByBallDetailedData.dismissalKind}
        {ballByBallDetailedData.playerDismissed}
        {ballByBallDetailedData.fielder}
        {ballByBallDetailedData.extrasType}
        {ballByBallDetailedData.battingTeam}
        {ballByBallDetailedData.bowlingTeam}
      </p>
    </div>
  );
};

export default BallByBallDetailsCard;
