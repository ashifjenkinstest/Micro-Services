package com.ashifs.repositories;

import java.time.LocalDate;
import java.util.List;

import com.ashifs.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

  List<Match> findTop4ByTeam1OrTeam2OrderByMatchDateDesc(String team1, String team2);

  List<Match> findByTeam1OrTeam2OrderByMatchDateDesc(String team1, String team2, Pageable pages);

  List<Match> findByTeam1AndMatchDateBetweenOrTeam2AndMatchDateBetweenOrderByMatchDateDesc(String team1,
      LocalDate startDate1, LocalDate endDate1, String team2, LocalDate startDate2, LocalDate endDate2);

  default List<Match> findTopFewByTeam1OrTeam2OrderByMatchDateDesc(String team, int count) {
    return findByTeam1OrTeam2OrderByMatchDateDesc(team, team, PageRequest.of(0, count));

  }

}