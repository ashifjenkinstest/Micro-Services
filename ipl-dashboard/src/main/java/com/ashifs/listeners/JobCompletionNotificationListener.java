package com.ashifs.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.ashifs.model.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Map<String, Team> teamData = new HashMap<>();

      entityManager.createQuery("SELECT M.team1,COUNT(*) FROM MATCH M GROUP BY M.team1", Object[].class).getResultList()
          .stream().map(e -> new Team((String) e[0], (long) e[1]))
          .forEach(team -> teamData.put(team.getTeamName(), team));

      entityManager.createQuery("SELECT M.team2,COUNT(*) FROM MATCH M GROUP BY M.team2", Object[].class).getResultList()
          .stream().forEach(e -> {
            Team team = teamData.get((String) e[0]);
            if (team != null) {

              team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
              teamData.put(team.getTeamName(), team);

            }
          });

      entityManager.createQuery("SELECT M.winner,COUNT(*) FROM MATCH M GROUP BY M.winner", Object[].class)
          .getResultList().stream().forEach(e -> {
            Team team = teamData.get((String) e[0]);
            if (team != null) {

              team.setTotalWins((long) e[1]);
              teamData.put(team.getTeamName(), team);

            }
          });

      teamData.values().forEach(team -> entityManager.persist(team));
      // teamData.values().forEach(team -> System.out.println(team.toString()));

      /*
       * jdbcTemplate.
       * query("SELECT team1,team2 ,winner,toss_winner,toss_deCiSion,id FROM IPL_MATCH"
       * , (rs, row) -> "Team 1 " + rs.getString(1) + " vs Team 2" + " " +
       * rs.getString(2) + " WINNER " + rs.getString(3) + " TOSS WINNER " +
       * rs.getString(4) + " TOSS DECISION " + rs.getString(5) + " ID " +
       * rs.getString(6)) .forEach(str -> System.out.println(str));
       * 
       */
    }
  }
}