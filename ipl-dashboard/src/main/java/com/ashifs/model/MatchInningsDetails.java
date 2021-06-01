package com.ashifs.model;

public class MatchInningsDetails {

    private String firstInningTeam;
    private Long firstInningRuns;
    private Long firstInningOvers;
    private Long firstInningWickets;

    private String secondInningTeam;

    private Long secondInningRuns;
    private Long secondInningOvers;
    private Long secondInningWickets;

    public MatchInningsDetails() {
    }

    public MatchInningsDetails(String firstInningTeam, Long firstInningRuns, Long firstInningOvers,
            Long firstInningWickets, String secondInningTeam, Long secondInningRuns, Long secondInningOvers,
            Long secondInningWickets) {
        this.firstInningTeam = firstInningTeam;
        this.firstInningRuns = firstInningRuns;
        this.firstInningOvers = firstInningOvers;
        this.firstInningWickets = firstInningWickets;
        this.secondInningTeam = secondInningTeam;
        this.secondInningRuns = secondInningRuns;
        this.secondInningOvers = secondInningOvers;
        this.secondInningWickets = secondInningWickets;
    }

    public String getFirstInningTeam() {
        return firstInningTeam;
    }

    public void setFirstInningTeam(String firstInningTeam) {
        this.firstInningTeam = firstInningTeam;
    }

    public Long getFirstInningRuns() {
        return firstInningRuns;
    }

    public void setFirstInningRuns(Long firstInningRuns) {
        this.firstInningRuns = firstInningRuns;
    }

    public Long getFirstInningOvers() {
        return firstInningOvers;
    }

    public void setFirstInningOvers(Long firstInningOvers) {
        this.firstInningOvers = firstInningOvers;
    }

    public Long getFirstInningWickets() {
        return firstInningWickets;
    }

    public void setFirstInningWickets(Long firstInningWickets) {
        this.firstInningWickets = firstInningWickets;
    }

    public String getSecondInningTeam() {
        return secondInningTeam;
    }

    public void setSecondInningTeam(String secondInningTeam) {
        this.secondInningTeam = secondInningTeam;
    }

    public Long getSecondInningRuns() {
        return secondInningRuns;
    }

    public void setSecondInningRuns(Long secondInningRuns) {
        this.secondInningRuns = secondInningRuns;
    }

    public Long getSecondInningOvers() {
        return secondInningOvers;
    }

    public void setSecondInningOvers(Long secondInningOvers) {
        this.secondInningOvers = secondInningOvers;
    }

    public Long getSecondInningWickets() {
        return secondInningWickets;
    }

    public void setSecondInningWickets(Long secondInningWickets) {
        this.secondInningWickets = secondInningWickets;
    }

    @Override
    public String toString() {
        return "MatchInningsDetails [firstInningOverv=" + firstInningOvers + ", firstInningRuns=" + firstInningRuns
                + ", firstInningTeam=" + firstInningTeam + ", firstInningWickets=" + firstInningWickets
                + ", secondInningOverv=" + secondInningOvers + ", secondInningRuns=" + secondInningRuns
                + ", secondInningTeam=" + secondInningTeam + ", secondInningWickets=" + secondInningWickets + "]";
    }

}
