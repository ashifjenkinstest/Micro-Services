package com.ashifs.controllers;

import java.time.LocalDate;
import java.util.List;

import com.ashifs.model.Match;
import com.ashifs.model.Matches;
import com.ashifs.model.Team;
import com.ashifs.model.Teams;
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

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
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

        team.setLastestMatches(this.matchRepository.findTop5ByTeam1OrTeam2OrderByMatchDateDesc(teamName, teamName));

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

}
