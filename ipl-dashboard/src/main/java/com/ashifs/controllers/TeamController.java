package com.ashifs.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ashifs.model.BallByBalls;
import com.ashifs.model.MatchStatisticsLost;
import com.ashifs.model.MatchStatisticsWon;
import com.ashifs.model.Matches;
import com.ashifs.model.Team;
import com.ashifs.model.TeamStat;
import com.ashifs.model.TeamStatistics;
import com.ashifs.model.Teams;
import com.ashifs.repositories.BallByBallRepositories;
import com.ashifs.repositories.MatchRepository;
import com.ashifs.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    private BallByBallRepositories ballRepositories;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository,
            BallByBallRepositories ballByBallRepositories) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.ballRepositories = ballByBallRepositories;
    }

    @RequestMapping(path = "/teams")
    public Teams getAllTeams(String teamName) {
        Teams teams = new Teams(this.teamRepository.findAll());

        return teams;
    }

    @RequestMapping(path = "/teams/{teamName}")
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
    public Matches getMatchOfTeam(@PathVariable String teamName) {

        return new Matches(this.matchRepository.findTopFewByTeam1OrTeam2OrderByMatchDateDesc(teamName, 4));

    }

    @RequestMapping(path = "/teams/{teamName}/matches")
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
    public BallByBalls getMatchOfTeamByMatchId(@RequestParam Long matchId) {
        System.out.println(">----> " + matchId);
        return new BallByBalls(ballRepositories.findByMatchIdOrderByInningAndOverAndBallAsc(matchId));
    }

    @RequestMapping(path = "/teams/{teamName}/statistics")
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
}
