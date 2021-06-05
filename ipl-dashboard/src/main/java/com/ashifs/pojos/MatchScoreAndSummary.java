package com.ashifs.pojos;

import com.ashifs.model.Match;

public class MatchScoreAndSummary {

    private MatchInningsDetails matchInningsScore;
    private Match match;

    public MatchScoreAndSummary() {
    }

    public MatchScoreAndSummary(MatchInningsDetails matchInningsScore, Match match) {
        this.matchInningsScore = matchInningsScore;
        this.match = match;
    }

    public MatchInningsDetails getMatchInningsScore() {
        return matchInningsScore;
    }

    public void setMatchInningsScore(MatchInningsDetails matchInningsScore) {
        this.matchInningsScore = matchInningsScore;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "MatchScoreAndSummary [match=" + match + ", matchInningsScore=" + matchInningsScore + "]";
    }

}
