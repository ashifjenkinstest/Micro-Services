package com.ashifs.model;

public class PlayerProfile {

    private String playerName;
    private Long wickets;
    private Long runs;
    private Long runOuts;
    private Long catches;
    private Long stumps;
    private Long mom;
    private Long sixes;
    private Long fours;
    private Long maxWicketsInMatch;
    private Long thirtyPlusRunsInMatch;
    private Long fiftyPlusRunsInMatch;
    private Long hundredPlusRunsInMatch;
    private Long bestBowlingFigureWickets;
    private Long bestBowlingFigureRuns;

    public PlayerProfile() {
    }

    public PlayerProfile(String playerName, Long wickets, Long runs, Long runOuts, Long catches, Long stumps, Long mom,
            Long sixes, Long fours, Long maxWicketsInMatch, Long thirtyPlusRunsInMatch, Long fiftyPlusRunsInMatch,
            Long hundredPlusRunsInMatch, Long bestBowlingFigureWickets, Long bestBowlingFigureRuns) {
        this.playerName = playerName;
        this.wickets = wickets;
        this.runs = runs;
        this.runOuts = runOuts;
        this.catches = catches;
        this.stumps = stumps;
        this.mom = mom;
        this.sixes = sixes;
        this.fours = fours;
        this.maxWicketsInMatch = maxWicketsInMatch;
        this.thirtyPlusRunsInMatch = thirtyPlusRunsInMatch;
        this.fiftyPlusRunsInMatch = fiftyPlusRunsInMatch;
        this.hundredPlusRunsInMatch = hundredPlusRunsInMatch;
        this.bestBowlingFigureWickets = bestBowlingFigureWickets;
        this.bestBowlingFigureRuns = bestBowlingFigureRuns;
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

    public Long getSixes() {
        return sixes;
    }

    public void setSixes(Long sixes) {
        this.sixes = sixes;
    }

    public Long getFours() {
        return fours;
    }

    public void setFours(Long fours) {
        this.fours = fours;
    }

    public Long getMaxWicketsInMatch() {
        return maxWicketsInMatch;
    }

    public void setMaxWicketsInMatch(Long maxWicketsInMatch) {
        this.maxWicketsInMatch = maxWicketsInMatch;
    }

    public Long getThirtyPlusRunsInMatch() {
        return thirtyPlusRunsInMatch;
    }

    public void setThirtyPlusRunsInMatch(Long thirtyPlusRunsInMatch) {
        this.thirtyPlusRunsInMatch = thirtyPlusRunsInMatch;
    }

    public Long getFiftyPlusRunsInMatch() {
        return fiftyPlusRunsInMatch;
    }

    public void setFiftyPlusRunsInMatch(Long fiftyPlusRunsInMatch) {
        this.fiftyPlusRunsInMatch = fiftyPlusRunsInMatch;
    }

    public Long getHundredPlusRunsInMatch() {
        return hundredPlusRunsInMatch;
    }

    public void setHundredPlusRunsInMatch(Long hundredPlusRunsInMatch) {
        this.hundredPlusRunsInMatch = hundredPlusRunsInMatch;
    }

    public Long getBestBowlingFigureWickets() {
        return bestBowlingFigureWickets;
    }

    public void setBestBowlingFigureWickets(Long bestBowlingFigureWickets) {
        this.bestBowlingFigureWickets = bestBowlingFigureWickets;
    }

    public Long getBestBowlingFigureRuns() {
        return bestBowlingFigureRuns;
    }

    public void setBestBowlingFigureRuns(Long bestBowlingFigureRuns) {
        this.bestBowlingFigureRuns = bestBowlingFigureRuns;
    }

    @Override
    public String toString() {
        return "PlayerProfile [bestBowlingFigureRuns=" + bestBowlingFigureRuns + ", bestBowlingFigureWickets="
                + bestBowlingFigureWickets + ", catches=" + catches + ", fiftyPlusRunsInMatch=" + fiftyPlusRunsInMatch
                + ", fours=" + fours + ", hundredPlusRunsInMatch=" + hundredPlusRunsInMatch + ", maxWicketsInMatch="
                + maxWicketsInMatch + ", mom=" + mom + ", playerName=" + playerName + ", runOuts=" + runOuts + ", runs="
                + runs + ", sixes=" + sixes + ", stumps=" + stumps + ", thirtyPlusRunsInMatch=" + thirtyPlusRunsInMatch
                + ", wickets=" + wickets + "]";
    }

}
