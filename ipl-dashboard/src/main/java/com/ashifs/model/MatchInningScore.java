package com.ashifs.model;

public class MatchInningScore {

    private String team;
    private Long inning;
    private Long runs;
    private Long wickets;
    private Long overs;

    public MatchInningScore() {
    }

    public MatchInningScore(String team, Long inning, Long runs, Long wickets, Long overs) {
        this.team = team;
        this.inning = inning;
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getInning() {
        return inning;
    }

    public void setInning(Long inning) {
        this.inning = inning;
    }

    public Long getRuns() {
        return runs;
    }

    public void setRuns(Long runs) {
        this.runs = runs;
    }

    public Long getWickets() {
        return wickets;
    }

    public void setWickets(Long wickets) {
        this.wickets = wickets;
    }

    public Long getOvers() {
        return overs;
    }

    public void setOvers(Long overs) {
        this.overs = overs;
    }

    @Override
    public String toString() {
        return "MatchScoreSummary [inning=" + inning + ", overs=" + overs + ", runs=" + runs + ", team=" + team
                + ", wickets=" + wickets + "]";
    }

}
