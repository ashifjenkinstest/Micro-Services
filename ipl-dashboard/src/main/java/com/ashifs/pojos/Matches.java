package com.ashifs.pojos;

import java.util.List;

import com.ashifs.model.Match;

public class Matches {

    List<Match> match;

    public Matches() {
    }

    public Matches(List<Match> match) {
        this.match = match;
    }

    public List<Match> getMatch() {
        return match;
    }

    public void setMatch(List<Match> match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Matches [match=" + match + "]";
    }

}
