package com.ashifs.pojos;

public class PlayerAndAttribute {

    private String player;
    private Long attr;

    public PlayerAndAttribute() {
    }

    public PlayerAndAttribute(String player, Long attr) {
        this.player = player;
        this.attr = attr;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Long getAttr() {
        return attr;
    }

    public void setAttr(Long attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "PlayerAndAttribute [attr=" + attr + ", player=" + player + "]";
    }

}
