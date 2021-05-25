package com.ashifs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "BALL_BY_BALL")
public class BallByBall {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "B_BY_B_SEQ")
    @SequenceGenerator(name = "B_BY_B_SEQ", sequenceName = "B_BY_B_SEQ", allocationSize = 1)
    private Long Id;
    private Long matchId;
    private Long inning;
    private Long over;
    private Long ball;
    private String batsman;
    private String nonStriker;
    private String bowler;
    private Long batsmanRuns;
    private Long extraRuns;
    private Long totalRuns;
    private Long nonBoundary;
    private Long isWicket;
    private String dismissalKind;
    private String playerDismissed;
    private String fielder;
    private String extrasType;
    private String battingTeam;
    private String bowlingTeam;

    public BallByBall() {
    }

    public BallByBall(Long matchId, Long inning, Long over, Long ball, String batsman, String nonStriker, String bowler,
            Long batsmanRuns, Long extraRuns, Long totalRuns, Long nonBoundary, Long isWicket, String dismissalKind,
            String playerDismissed, String fielder, String extrasType, String battingTeam, String bowlingTeam) {
        this.matchId = matchId;
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

    public BallByBall(Long id, Long matchId, Long inning, Long over, Long ball, String batsman, String nonStriker,
            String bowler, Long batsmanRuns, Long extraRuns, Long totalRuns, Long nonBoundary, Long isWicket,
            String dismissalKind, String playerDismissed, String fielder, String extrasType, String battingTeam,
            String bowlingTeam) {
        Id = id;
        this.matchId = matchId;
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getInning() {
        return inning;
    }

    public void setInning(Long inning) {
        this.inning = inning;
    }

    public Long getOver() {
        return over;
    }

    public void setOver(Long over) {
        this.over = over;
    }

    public Long getBall() {
        return ball;
    }

    public void setBall(Long ball) {
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

    public Long getBatsmanRuns() {
        return batsmanRuns;
    }

    public void setBatsmanRuns(Long batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public Long getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(Long extraRuns) {
        this.extraRuns = extraRuns;
    }

    public Long getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(Long totalRuns) {
        this.totalRuns = totalRuns;
    }

    public Long getNonBoundary() {
        return nonBoundary;
    }

    public void setNonBoundary(Long nonBoundary) {
        this.nonBoundary = nonBoundary;
    }

    public Long getIsWicket() {
        return isWicket;
    }

    public void setIsWicket(Long isWicket) {
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
        return "BallByBall [Id=" + Id + ", ball=" + ball + ", batsman=" + batsman + ", batsmanRuns=" + batsmanRuns
                + ", battingTeam=" + battingTeam + ", bowler=" + bowler + ", bowlingTeam=" + bowlingTeam
                + ", dismissalKind=" + dismissalKind + ", extraRuns=" + extraRuns + ", extrasType=" + extrasType
                + ", fielder=" + fielder + ", inning=" + inning + ", isWicket=" + isWicket + ", matchId=" + matchId
                + ", nonBoundary=" + nonBoundary + ", nonStriker=" + nonStriker + ", over=" + over
                + ", playerDismissed=" + playerDismissed + ", totalRuns=" + totalRuns + "]";
    }

}
