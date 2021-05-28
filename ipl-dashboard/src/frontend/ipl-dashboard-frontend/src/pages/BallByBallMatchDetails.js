import React, { useState, useEffect } from "react";
import TeamImage from "../components/TeamImage";
import "../csss/BallByBallMatchDetails.scss";
import { useParams, Link } from "react-router-dom";
import BallByBallDetailsCard from "../components/BallByBallDetailsCard";

function BallByBallMatchDetails() {
  const [ballByBalls, setBallByBalls] = useState({ ballByBall: [] });

  const { matchId } = useParams();

  let battingTeam;
  let bowlingTeam;

  useEffect(() => {
    const fetchMatchDetails = async () => {
      const response = await fetch(
        `${process.env.REACT_APP_API_ROOT_URL}/teams/matches?matchId=${matchId}`
      );
      const data = await response.json();
      setBallByBalls(data);
    };
    fetchMatchDetails();
  }, [matchId]);

  ballByBalls.ballByBall
    .slice(0, 1)
    .map((bbyb) => (battingTeam = bbyb.battingTeam));
  ballByBalls.ballByBall
    .slice(0, 1)
    .map((bbyb) => (bowlingTeam = bbyb.bowlingTeam));

  const rootTeamRoute1 = "/teams/" + battingTeam;
  const rootTeamRoute2 = "/teams/" + bowlingTeam;
  let totalRunsInInning = 0;
  let totalRunsInInning1 = 0;
  let totalWicketssInInning = 0;
  let totalWicketssInInning1 = 0;
  let totalRunsInFInningArr = [];
  let totalWicketsInFInningArr = [];
  let totalRunsInSInningArr = [];
  let totalWicketsInSInningArr = [];
  let rowcount = 0;
  ballByBalls.ballByBall.forEach((element, x) => {
    if (element.inning === 1) {
      totalRunsInInning =
        totalRunsInInning + element.extraRuns + element.batsmanRuns;
      totalRunsInFInningArr.push(totalRunsInInning);
      totalWicketssInInning = totalWicketssInInning + element.isWicket;
      totalWicketsInFInningArr.push(totalWicketssInInning);
      rowcount = x;
    }
    if (element.inning !== 1) {
      totalRunsInInning1 =
        totalRunsInInning1 + element.extraRuns + element.batsmanRuns;
      totalRunsInSInningArr.push(totalRunsInInning1);
      totalWicketssInInning1 = totalWicketssInInning1 + element.isWicket;
      totalWicketsInSInningArr.push(totalWicketssInInning1);
    }
  });

  return (
    <div className="BallByBallMatchDetails">
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
          <Link to={rootTeamRoute2}>
            <h3>{bowlingTeam}</h3>
          </Link>
        </div>
      </div>
      <div className="t1-vs-t2-header-section">
        <h1>Match Timeline</h1>
      </div>
      <div className="ball-by-ball-main-section">
        {ballByBalls.ballByBall.map((ballByBallData, idx, wick, run) =>
          ballByBallData.inning === 1
            ? ((run = totalRunsInFInningArr[idx]),
              (wick = totalWicketsInFInningArr[idx]),
              (
                <BallByBallDetailsCard
                  key={ballByBallData.id}
                  ballByBallDetailedData={ballByBallData}
                  fInningRuns={run}
                  fInningWickets={wick}
                />
              ))
            : ((run = totalRunsInSInningArr[idx - rowcount - 1]),
              (wick = totalWicketsInSInningArr[idx - rowcount - 1]),
              (
                <BallByBallDetailsCard
                  key={ballByBallData.id}
                  ballByBallDetailedData={ballByBallData}
                  sInningRuns={run}
                  sInningWickets={wick}
                />
              ))
        )}
      </div>
    </div>
  );
}
//<TeamPage key={team.teamName} team={team.teamName} />
export default BallByBallMatchDetails;
