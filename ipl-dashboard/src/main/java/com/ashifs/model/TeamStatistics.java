package com.ashifs.model;

public class TeamStatistics {

    private String team;
    private String opponent;
    private long won;
    private long lost;

    public TeamStatistics() {
    }

    public TeamStatistics(String team, String opponent, long won, long lost) {
        this.team = team;
        this.opponent = opponent;
        this.won = won;
        this.lost = lost;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public long getWon() {
        return won;
    }

    public void setWon(long won) {
        this.won = won;
    }

    public long getLost() {
        return lost;
    }

    public void setLost(long lost) {
        this.lost = lost;
    }

    @Override
    public String toString() {
        return "TeamStatistics [lost=" + lost + ", opponent=" + opponent + ", team=" + team + ", won=" + won + "]";
    }

}
