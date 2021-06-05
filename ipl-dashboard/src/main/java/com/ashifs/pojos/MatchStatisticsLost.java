package com.ashifs.pojos;

public class MatchStatisticsLost {

    private String winner;

    private String opponent;

    private long lost;

    public MatchStatisticsLost(String winner, String opponent, long lost) {
        this.winner = winner;
        this.opponent = opponent;
        this.lost = lost;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public long getLost() {
        return lost;
    }

    public void setLost(long lost) {
        this.lost = lost;
    }

    @Override
    public String toString() {
        return "MatchStatisticsLost [lost=" + lost + ", opponent=" + opponent + ", winner=" + winner + "]";
    }

}
