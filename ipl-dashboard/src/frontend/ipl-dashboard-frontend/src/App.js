import "./App.css";
import AllTeams from "./pages/AllTeams";
import AllTeamsPage from "./pages/AllTeams";
import TeamPage from "./pages/TeamPage";
import { BrowserRouter as Router, Route } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Router>
        <Route>
          <Route path="/teams/:rootTeamName">
            <TeamPage />
          </Route>
        </Route>
      </Router>
    </div>
  );
}
// <Route path="/teams/:rootTeamName">
//<TeamPage />
//</Route>
export default App;
