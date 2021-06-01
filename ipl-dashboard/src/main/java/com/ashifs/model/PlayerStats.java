package com.ashifs.model;

public class PlayerStats {

    private PlayerProfile playerProfile;
    private MatchScoreAndSummary matchScoreAndSummary;

    public PlayerStats() {
    }

    public PlayerStats(PlayerProfile playerProfile, MatchScoreAndSummary matchScoreAndSummary) {
        this.playerProfile = playerProfile;
        this.matchScoreAndSummary = matchScoreAndSummary;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public MatchScoreAndSummary getMatchScoreAndSummary() {
        return matchScoreAndSummary;
    }

    public void setMatchScoreAndSummary(MatchScoreAndSummary matchScoreAndSummary) {
        this.matchScoreAndSummary = matchScoreAndSummary;
    }

    @Override
    public String toString() {
        return "PlayerStats [matchScoreAndSummary=" + matchScoreAndSummary + ", playerProfile=" + playerProfile + "]";
    }

}
