package com.ashifs.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashifs.model.Team;
import com.ashifs.pojos.BallByBalls;
import com.ashifs.pojos.MatchStatisticsLost;
import com.ashifs.pojos.MatchStatisticsWon;
import com.ashifs.pojos.Matches;
import com.ashifs.pojos.PlayerStats;
import com.ashifs.pojos.TeamStat;
import com.ashifs.pojos.TeamStatistics;
import com.ashifs.pojos.Teams;
import com.ashifs.repositories.BallByBallRepositories;
import com.ashifs.repositories.MatchRepository;
import com.ashifs.repositories.TeamRepository;
import com.ashifs.services.PlayerProfileServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/ipl")
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    private BallByBallRepositories ballRepositories;
    private PlayerProfileServiceImpl playerProfileServiceImpl;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository,
            BallByBallRepositories ballByBallRepositories, PlayerProfileServiceImpl playerProfileServiceImpl) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.ballRepositories = ballByBallRepositories;
        this.playerProfileServiceImpl = playerProfileServiceImpl;
    }

    @RequestMapping(path = "/teams")
    @ApiOperation(value = "Get all the IPL Teams since 2008", notes = "Provides the teams which do not play anymore  also", response = Teams.class)
    public Teams getAllTeams(String teamName) {
        Teams teams = new Teams(this.teamRepository.findAll());

        return teams;
    }

    @RequestMapping(path = "/teams/{teamName}")
    @ApiOperation(value = "Get details of a team", notes = "Provided a team it will return details of that team", response = Team.class)
    public Team getTeam(@PathVariable String teamName) {
        Team team = new Team();
        Team teamInternal = this.teamRepository.findByTeamName(teamName);
        team.setId(teamInternal.getId());
        team.setTeamName(teamInternal.getTeamName());
        team.setTotalMatches(teamInternal.getTotalMatches());
        team.setTotalWins(teamInternal.getTotalWins());

        team.setLastestMatches(this.matchRepository.findTop4ByTeam1OrTeam2OrderByMatchDateDesc(teamName, teamName));

        return team;

    }

    @RequestMapping(path = "/matches/{teamName}")
    @ApiOperation(value = "Get details of the latest four matches played by a team", notes = "Provided a team it will return details of the matches played by that team", response = Matches.class)
    public Matches getMatchOfTeam(@PathVariable String teamName) {

        return new Matches(this.matchRepository.findTopFewByTeam1OrTeam2OrderByMatchDateDesc(teamName, 4));

    }

    @RequestMapping(path = "/teams/{teamName}/matches")
    @ApiOperation(value = "Get details of the matches played by a team since a certain year", notes = "Provided a team and a year it will return details of the matches played by that team since the year 2XXX", response = Matches.class)
    public Matches getMatchOfTeamByDateRanges(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1).getYear() > LocalDate.now().getYear()
                ? LocalDate.of(year, 1, 1).plusMonths(12)
                : LocalDate.of(year + 1, 1, 1);
        System.out.println(startDate + " " + endDate);
        return new Matches(
                this.matchRepository.findByTeam1AndMatchDateBetweenOrTeam2AndMatchDateBetweenOrderByMatchDateDesc(
                        teamName, startDate, LocalDate.now(), teamName, startDate, LocalDate.now()));

    }

    @RequestMapping(path = "/teams/matches")
    @ApiOperation(value = "Get details of the matche of specific matchID", notes = "Provided a matchID it will return details of the matche", response = BallByBalls.class)
    public BallByBalls getMatchOfTeamByMatchId(@RequestParam Long matchId) {
        System.out.println(">----> " + matchId);
        return new BallByBalls(ballRepositories.findByMatchIdOrderByInningAndOverAndBallAsc(matchId));
    }

    @RequestMapping(path = "/teams/{teamName}/statistics")
    @ApiOperation(value = "Get the match statistics of a Team", notes = "Provided a team name it will return the statistics of the Team", response = TeamStat.class)
    public TeamStat getMatchStatisticsByTeamName(@PathVariable String teamName) {

        List<TeamStatistics> teamStatistics = new ArrayList<>();
        TeamStat teamStatReturn = new TeamStat();
        Map<String, TeamStatistics> teamStat = new HashMap<String, TeamStatistics>();
        System.out.println("Initial list of elements: " + teamStat);
        for (MatchStatisticsWon iterable_element : this.matchRepository.findByWinnerGroupByOpponent(teamName)) {
            // System.out.println(iterable_element.getOpponent());
            teamStat.put(iterable_element.getOpponent(), new TeamStatistics(iterable_element.getWinner(),
                    iterable_element.getOpponent(), iterable_element.getWon(), 0L));
        }
        for (MatchStatisticsLost iterable_element : this.matchRepository.findByLoserGroupByOpponent(teamName)) {
            if (teamStat.get(iterable_element.getWinner()) == null)
                teamStat.put(iterable_element.getOpponent(), new TeamStatistics(iterable_element.getOpponent(),
                        iterable_element.getWinner(), 0L, iterable_element.getLost()));
            else
                teamStat.get(iterable_element.getWinner()).setLost(iterable_element.getLost()); // System.out.println(iterable_element.getWinner());
        }

        teamStat.values().forEach(team -> teamStatistics.add(team));
        teamStatReturn.setStatistics(teamStatistics);
        return teamStatReturn;
    }

    @RequestMapping(path = "/match/ipl/players/{matchId}/{player}")
    @ApiOperation(value = "Get the statistics of the player and details of a match", notes = "Provided a player name  and match id it will return the statistics of the Player and details of the match", response = PlayerStats.class)
    public PlayerStats getPlayerProfile(@PathVariable Long matchId, @PathVariable String player) {
        System.out.println("Inside getPlayerProfile");
        PlayerStats playerStats = new PlayerStats();
        playerStats.setPlayerProfile(playerProfileServiceImpl.getPlayerProfile(player));
        playerStats.setMatchScoreAndSummary(playerProfileServiceImpl.getMatchScoreAndSummaryByMatchId(matchId));
        return playerStats;
    }
}
