package com.ashifs.controllers;

import java.util.List;

import com.ashifs.model.Match;
import com.ashifs.model.Team;
import com.ashifs.repositories.MatchRepository;
import com.ashifs.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {


    private TeamRepository teamRepository;
    private MatchRepository matchRepository;


    

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @RequestMapping(path = "/teams")
    public List<Team> getAllTeams(String teamName){

        return this.teamRepository.findAll();

    }

    @RequestMapping(path = "/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName){
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
    public List<Match> getMatchOfTeam(@PathVariable String teamName){
    
        return this.matchRepository.findTopFewByTeam1OrTeam2OrderByMatchDateDesc(teamName,4);

    }

    
}
