package com.ashifs.model;

public class MatchStatisticsWon {

    private String winner;

    private String opponent;

    private long won;

    public MatchStatisticsWon() {
    }

    public MatchStatisticsWon(String winner, String opponent, long won) {
        this.winner = winner;
        this.opponent = opponent;
        this.won = won;
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

    public long getWon() {
        return won;
    }

    public void setWon(long won) {
        this.won = won;
    }

    @Override
    public String toString() {
        return "MatchStatistics [opponent=" + opponent + ", winner=" + winner + ", won=" + won + "]";
    }

}
