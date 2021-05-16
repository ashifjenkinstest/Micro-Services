import React from "react";
import { Link } from "react-router-dom";
import "../csss/YearSelector.scss";

export const YearSelector = ({ team, year }) => {
  let years = [];
  const startYear = process.env.REACT_APP_IPL_START_YEAR;
  const lastYear = process.env.REACT_APP_IPL_LAST_YEAR;

  for (let i = startYear; i <= lastYear; i++) {
    years.push(i);
  }

  return (
    <div className="YearSelector">
      <ul>
        <div className="year-section">
          <li>
            <h3>Select Year</h3>
          </li>
        </div>
        {years.map((year) => (
          <div className="year-section">
            <li>
              <Link to={`/teams/${team}/matches/${year}`}>{year}</Link>
            </li>
          </div>
        ))}
      </ul>
    </div>
  );
};

export default YearSelector;
