package com.ashifs.model;

public class BowlerAttrs {

    String bowlerName;
    Long matchId;
    Long wicketsOrRuns;

    public BowlerAttrs() {
    }

    public BowlerAttrs(String bowlerName, Long matchId, Long wicketsOrRuns) {
        this.bowlerName = bowlerName;
        this.matchId = matchId;
        this.wicketsOrRuns = wicketsOrRuns;
    }

    public String getBowlerName() {
        return bowlerName;
    }

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getWicketsOrRuns() {
        return wicketsOrRuns;
    }

    public void setWicketsOrRuns(Long wicketsOrRuns) {
        this.wicketsOrRuns = wicketsOrRuns;
    }

    @Override
    public String toString() {
        return "BowlerAttrs [bowlerName=" + bowlerName + ", matchId=" + matchId + ", wicketsOrRuns=" + wicketsOrRuns
                + "]";
    }

}
