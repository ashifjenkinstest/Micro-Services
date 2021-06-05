package com.ashifs.pojos;

public class BestBowlingFigure {

    private String bowler;
    private Long wickets;
    private Long runs;

    public BestBowlingFigure() {
    }

    public BestBowlingFigure(String bowler, Long wickets, Long runs) {
        this.bowler = bowler;
        this.wickets = wickets;
        this.runs = runs;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
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

    @Override
    public String toString() {
        return "BestBowlingFigure [bowler=" + bowler + ", runs=" + runs + ", wickets=" + wickets + "]";
    }

}
