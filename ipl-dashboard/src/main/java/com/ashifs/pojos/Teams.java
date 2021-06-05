package com.ashifs.pojos;

import java.util.List;

import com.ashifs.model.Team;

public class Teams {

    private List<Team> teams;

    public Teams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Teams [teams=" + teams + "]";
    }

}
