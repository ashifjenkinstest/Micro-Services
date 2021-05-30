package com.ashifs.model;

public class PlayerStats {

    private PlayerProfile playerProfile;

    public PlayerStats() {
    }

    public PlayerStats(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    @Override
    public String toString() {
        return "PlayerStats [playerProfile=" + playerProfile + "]";
    }

}
