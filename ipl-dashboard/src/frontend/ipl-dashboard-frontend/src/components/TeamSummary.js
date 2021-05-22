import React from "react";

export const TeamSummaryCard = () => {
  return (
    <div className="TeamSummaryCard">
      <table>
        <tr>
          <th>id</th>
          <th>teamName</th>
          <th>totalMatches</th>
          <th>totalWins</th>
        </tr>
        <tr>
          <td>1</td>
          <td>Mumbai Indians</td>
          <td>203</td>
          <td>120</td>
        </tr>
      </table>
    </div>
  );
};

export default TeamSummaryCard;
