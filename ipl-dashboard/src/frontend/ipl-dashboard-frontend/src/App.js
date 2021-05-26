import "./App.scss";
import TeamPage from "./pages/TeamPage";
import { HashRouter as Router, Route, Switch } from "react-router-dom";
import MatchPage from "./pages/MatchPage";
import TeamStatistics from "./components/TeamStatistics";
import AllTeams from "./pages/AllTeams";
import BallByBallMatchDetails from "./pages/BallByBallMatchDetails";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/teams/matches/:matchId">
            <BallByBallMatchDetails />
          </Route>
          <Route path="/teams/:rootTeamName/matches/:matchYear">
            <MatchPage />
          </Route>
          <Route path="/teams/:rootTeamName/statistics">
            <TeamStatistics />
          </Route>
          <Route path="/teams/:rootTeamName">
            <TeamPage />
          </Route>

          <Route path="">
            <AllTeams />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}
// <Route path="/teams/:rootTeamName">
//<TeamPage />
//</Route>
export default App;
