package com.ashifs.data;

public class BallByBallInput {

    private String id;
    private String inning;
    private String over;
    private String ball;
    private String batsman;
    private String nonStriker;
    private String bowler;
    private String batsmanRuns;
    private String extraRuns;
    private String totalRuns;
    private String nonBoundary;
    private String isWicket;
    private String dismissalKind;
    private String playerDismissed;
    private String fielder;
    private String extrasType;
    private String battingTeam;
    private String bowlingTeam;

    public BallByBallInput() {
    }

    public BallByBallInput(String matchId, String inning, String over, String ball, String batsman, String nonStriker,
            String bowler, String batsmanRuns, String extraRuns, String totalRuns, String nonBoundary, String isWicket,
            String dismissalKind, String playerDismissed, String fielder, String extrasType, String battingTeam,
            String bowlingTeam) {
        this.id = matchId;
        this.inning = inning;
        this.over = over;
        this.ball = ball;
        this.batsman = batsman;
        this.nonStriker = nonStriker;
        this.bowler = bowler;
        this.batsmanRuns = batsmanRuns;
        this.extraRuns = extraRuns;
        this.totalRuns = totalRuns;
        this.nonBoundary = nonBoundary;
        this.isWicket = isWicket;
        this.dismissalKind = dismissalKind;
        this.playerDismissed = playerDismissed;
        this.fielder = fielder;
        this.extrasType = extrasType;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
    }

    public String getId() {
        return id;
    }

    public void setId(String matchId) {
        this.id = matchId;
    }

    public String getInning() {
        return inning;
    }

    public void setInning(String inning) {
        this.inning = inning;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public String getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(String nonStriker) {
        this.nonStriker = nonStriker;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getBatsmanRuns() {
        return batsmanRuns;
    }

    public void setBatsmanRuns(String batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public String getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(String extraRuns) {
        this.extraRuns = extraRuns;
    }

    public String getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(String totalRuns) {
        this.totalRuns = totalRuns;
    }

    public String getNonBoundary() {
        return nonBoundary;
    }

    public void setNonBoundary(String nonBoundary) {
        this.nonBoundary = nonBoundary;
    }

    public String getIsWicket() {
        return isWicket;
    }

    public void setIsWicket(String isWicket) {
        this.isWicket = isWicket;
    }

    public String getDismissalKind() {
        return dismissalKind;
    }

    public void setDismissalKind(String dismissalKind) {
        this.dismissalKind = dismissalKind;
    }

    public String getPlayerDismissed() {
        return playerDismissed;
    }

    public void setPlayerDismissed(String playerDismissed) {
        this.playerDismissed = playerDismissed;
    }

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

    public String getExtrasType() {
        return extrasType;
    }

    public void setExtrasType(String extrasType) {
        this.extrasType = extrasType;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    @Override
    public String toString() {
        return "BallByBallInput [ball=" + ball + ", batsman=" + batsman + ", batsmanRuns=" + batsmanRuns
                + ", battingTeam=" + battingTeam + ", bowler=" + bowler + ", bowlingTeam=" + bowlingTeam
                + ", dismissalKind=" + dismissalKind + ", extraRuns=" + extraRuns + ", extrasType=" + extrasType
                + ", fielder=" + fielder + ", inning=" + inning + ", isWicket=" + isWicket + ", matchId=" + id
                + ", nonBoundary=" + nonBoundary + ", nonStriker=" + nonStriker + ", over=" + over
                + ", playerDismissed=" + playerDismissed + ", totalRuns=" + totalRuns + "]";
    }

}
