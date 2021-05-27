import React, { useState, useEffect } from "react";
import TeamImage from "../components/TeamImage";
import "../csss/BallByBallMatchDetails.scss";
import { PieChart } from "react-minimal-pie-chart";
import { useParams, Link } from "react-router-dom";
import BallByBallDetailsCard from "../components/BallByBallDetailsCard";

function BallByBallMatchDetails() {
  const [ballByBalls, setBallByBalls] = useState({ ballByBall: [] });

  const { matchId } = useParams();

  useEffect(() => {
    const fetchMatchDetails = async () => {
      const response = await fetch(
        `${process.env.REACT_APP_API_ROOT_URL}/teams/matches?matchId=${matchId}`
      );
      const data = await response.json();
      setBallByBalls(data);
    };
    fetchMatchDetails();
  }, []);

  let battingTeam;
  let bowlingTeam;
  ballByBalls.ballByBall
    .slice(0, 1)
    .map((bbyb) => (battingTeam = bbyb.battingTeam));
  ballByBalls.ballByBall
    .slice(0, 1)
    .map((bbyb) => (bowlingTeam = bbyb.bowlingTeam));
  const rootTeamRoute1 = "/teams/" + battingTeam;
  const rootTeamRoute2 = "/teams/" + bowlingTeam;
  let totalRunsInInning = 0;
  return (
    <div className="BallByBallMatchDetails">
      <div className="t1-vs-t2-header-section">
        <h1>Head to Head</h1>
      </div>
      <div className="teamname-teamimage-section-1">
        <div className="team-image">
          <TeamImage team={battingTeam} />
        </div>
        <div className="team-name">
          <Link to={rootTeamRoute1}>
            <h3>{battingTeam}</h3>
          </Link>
        </div>
      </div>
      <div className="vs-section">
        <div className="vs-image">
          <TeamImage team="VS" />
        </div>
      </div>
      <div className="teamname-teamimage-section-2">
        <div className="team-image">
          <TeamImage team={bowlingTeam} />
        </div>
        <div className="team-name">
          <Link to={rootTeamRoute1}>
            <h3>{bowlingTeam}</h3>
          </Link>
        </div>
      </div>
      <div className="ball-by-ball-main-section">
        {ballByBalls.ballByBall.map((ballByBallData, totalWicketsInInning) => (
          <BallByBallDetailsCard
            key={ballByBallData.id}
            ballByBallDetailedData={ballByBallData}
            totalWicks={totalWicketsInInning}
          />
        ))}
      </div>
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default BallByBallMatchDetails;
