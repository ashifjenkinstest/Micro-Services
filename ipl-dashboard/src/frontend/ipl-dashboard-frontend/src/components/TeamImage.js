import React from "react";
import "../csss/MatchSummaryCard.scss";
import deflogo from "../images/DEF.jpg"; // Tell webpack this JS file uses this image
import csklogo from "../images/CSK.jpg";
import dcgrlogo from "../images/DCGR.jpg";
import dcplogo from "../images/DCP.jpg";
import ddlogo from "../images/DD.jpg";

import glnslogo from "../images/GLNS.jpg";
import kkrlogo from "../images/KKR.jpg";
import kxiplogo from "../images/KXIP.jpg";
import milogo from "../images/MI.jpg";
import rrlogo from "../images/RR.jpg";
import srhlogo from "../images/SRH.jpg";
import rcblogo from "../images/RCB.jpg";
import ktklogo from "../images/KTK.jpg";
import rpslogo from "../images/RPS.png";
import pwilogo from "../images/PWI.png";
import ipllogo from "../images/IPL.jpg";
import vslogo from "../images/VS.png";
import playerlogo from "../images/PLAYER.png";

export const TeamImage = ({ team }) => {
  let logoName = deflogo;
  //console.log("In image team " + team);
  if (team === "IPL") {
    logoName = ipllogo;
  }
  if (team === "Mumbai Indians") {
    logoName = milogo;
  }
  if (team === "Pune Warriors") {
    logoName = pwilogo;
  }
  if (team === "Sunrisers Hyderabad") {
    logoName = srhlogo;
  }
  if (team === "Rajasthan Royals") {
    logoName = rrlogo;
  }
  if (team === "Royal Challengers Bangalore") {
    logoName = rcblogo;
  }
  if (team === "Kolkata Knight Riders") {
    logoName = kkrlogo;
  }
  if (team === "Gujarat Lions") {
    logoName = glnslogo;
  }
  if (team === "Rising Pune Supergiants") {
    logoName = rpslogo;
  }
  if (team === "Kochi Tuskers Kerala") {
    logoName = ktklogo;
  }
  if (team === "Delhi Capitals") {
    logoName = dcplogo;
  }
  if (team === "Kings XI Punjab") {
    logoName = kxiplogo;
  }
  if (team === "Deccan Chargers") {
    logoName = dcgrlogo;
  }
  if (team === "Delhi Daredevils") {
    logoName = ddlogo;
  }
  if (team === "Chennai Super Kings") {
    logoName = csklogo;
  }
  if (team === "VS") {
    logoName = vslogo;
  }
  if (team === "PLAYER") {
    logoName = playerlogo;
  }

  return (
    <React.Fragment>
      <span>
        <img src={logoName} alt="Logo" height="200" width="200" />
      </span>
      <br />
    </React.Fragment>
  );
};

export default TeamImage;
