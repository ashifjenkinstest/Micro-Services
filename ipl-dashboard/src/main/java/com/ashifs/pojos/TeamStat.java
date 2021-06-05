package com.ashifs.pojos;

import java.util.List;

public class TeamStat {

    private List<TeamStatistics> statistics;

    public TeamStat() {
    }

    public TeamStat(List<TeamStatistics> statistics) {
        this.statistics = statistics;
    }

    public List<TeamStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<TeamStatistics> statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "TeamStat [statistics=" + statistics + "]";
    }

}
