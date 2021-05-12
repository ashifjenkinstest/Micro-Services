import "./App.css";
import AllTeams from "./pages/AllTeams";
import AllTeamsPage from "./pages/AllTeams";
import TeamPage from "./pages/TeamPage";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import MatchPage from "./pages/MatchPage";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/teams/:rootTeamName/matches/:matchYear">
            <MatchPage />
          </Route>
          <Route path="/teams/:rootTeamName">
            <TeamPage />
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
