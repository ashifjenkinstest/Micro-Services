package com.ashifs.model;

public class PlayerProfile {

    private String playerName;
    private Long wickets;
    private Long runs;
    private Long runOuts;
    private Long catches;
    private Long stumps;
    private Long mom;

    public PlayerProfile() {
    }

    public PlayerProfile(String playerName, Long wickets, Long runs, Long runOuts, Long catches, Long stumps,
            Long mom) {
        this.playerName = playerName;
        this.wickets = wickets;
        this.runs = runs;
        this.runOuts = runOuts;
        this.catches = catches;
        this.stumps = stumps;
        this.mom = mom;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getWickets() {
        return wickets;
    }

    public void setWickets(Long wickets) {
        this.wickets = wickets;
    }

    public Long getRuns() {
        return runs;
    }

    public void setRuns(Long runs) {
        this.runs = runs;
    }

    public Long getRunOuts() {
        return runOuts;
    }

    public void setRunOuts(Long runOuts) {
        this.runOuts = runOuts;
    }

    public Long getCatches() {
        return catches;
    }

    public void setCatches(Long catches) {
        this.catches = catches;
    }

    public Long getStumps() {
        return stumps;
    }

    public void setStumps(Long stumps) {
        this.stumps = stumps;
    }

    public Long getMom() {
        return mom;
    }

    public void setMom(Long mom) {
        this.mom = mom;
    }

    @Override
    public String toString() {
        return "PlayerProfile [catches=" + catches + ", mom=" + mom + ", playerName=" + playerName + ", runOuts="
                + runOuts + ", runs=" + runs + ", stumps=" + stumps + ", wickets=" + wickets + "]";
    }

}
