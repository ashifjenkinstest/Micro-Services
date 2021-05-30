package com.ashifs.repositories;

import java.time.LocalDate;
import java.util.List;
import com.ashifs.model.Match;
import com.ashifs.model.MatchStatisticsLost;
import com.ashifs.model.MatchStatisticsWon;
import com.ashifs.model.PlayerAndAttribute;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

  List<Match> findTop4ByTeam1OrTeam2OrderByMatchDateDesc(String team1, String team2);

  List<Match> findByTeam1OrTeam2OrderByMatchDateDesc(String team1, String team2, Pageable pages);

  @Query("select new com.ashifs.model.MatchStatisticsWon(m.winner ,m.opponent ,count(*)) from com.ashifs.model.Match as m where m.winner = :teamName and m.winner!='NA' group by m.winner , m.opponent")
  List<MatchStatisticsWon> findByWinnerGroupByOpponent(String teamName);

  @Query("select new com.ashifs.model.MatchStatisticsLost(m.winner , m.opponent , count(*) ) from com.ashifs.model.Match as m where m.winner != :teamName and m.winner!='NA' and (Team1=:teamName or Team2=:teamName ) group by m.winner ,m.opponent")
  List<MatchStatisticsLost> findByLoserGroupByOpponent(String teamName);

  @Query("select new  com.ashifs.model.PlayerAndAttribute(m.playerOfMatch, count(*))  FROM com.ashifs.model.Match as m where m.playerOfMatch=:player")
  PlayerAndAttribute findNoOfMOMByPlayer(String player);

  List<Match> findByTeam1AndMatchDateBetweenOrTeam2AndMatchDateBetweenOrderByMatchDateDesc(String team1,
      LocalDate startDate1, LocalDate endDate1, String team2, LocalDate startDate2, LocalDate endDate2);

  default List<Match> findTopFewByTeam1OrTeam2OrderByMatchDateDesc(String team, int count) {
    return findByTeam1OrTeam2OrderByMatchDateDesc(team, team, PageRequest.of(0, count));

  }

}
