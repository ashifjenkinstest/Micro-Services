package com.ashifs.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;

import com.ashifs.model.BallByBall;
import com.ashifs.pojos.BestBowlingFigure;
import com.ashifs.pojos.BowlerAttrs;
import com.ashifs.pojos.MatchInningScore;
import com.ashifs.pojos.PlayerAndAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BallByBallRepositories extends JpaRepository<BallByBall, Long> {

        List<BallByBall> findByMatchIdOrderByInningAsc(Long matchId, Sort sort);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.bowler, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.isWicket = 1 AND ((b.dismissalKind!='RUN OUT') OR (b.dismissalKind='RUN OUT' AND b.fielder = bowler)) AND b.bowlingTeam!='NA' AND b.bowler = :player GROUP BY b.bowler")
        PlayerAndAttribute findTotalWicketsofPlayer(String player);

        @Query(value = "select new  com.ashifs.pojos.BowlerAttrs(b.bowler,b.matchId, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.isWicket = 1 AND ((b.dismissalKind!='RUN OUT') OR (b.dismissalKind='RUN OUT' AND b.fielder = bowler)) AND b.bowlingTeam!='NA' AND b.bowler = :player GROUP BY b.bowler,b.matchId order by SUM(b.isWicket) desc ")
        List<BowlerAttrs> findMaxWicketsAndMatchIdofPlayer(String player);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.bowler, sum(b.totalRuns))  FROM com.ashifs.model.BallByBall as b  where b.bowler = :player and b.extrasType!='LEGBYES' and b.matchId=:matchId ")
        PlayerAndAttribute findTotalRunsInHighestWicketMatchofPlayer(String player, Long matchId);

        @Query("select new  com.ashifs.pojos.BestBowlingFigure(b.bowler ,sum(b.isWicket) ,sum(b.totalRuns))  "
                        + " FROM com.ashifs.model.BallByBall as b   where b.bowler = :player and b.extrasType!='LEGBYES'"
                        + " and"
                        + " ((b.dismissalKind!='RUN OUT' ) or (b.dismissalKind='RUN OUT' and b.fielder = b.bowler   )) "
                        + " and b.bowlingTeam!='NA' group by b.matchId order by sum(b.isWicket) desc, sum(b.totalRuns) asc")
        List<BestBowlingFigure> findBestBowlingFigurefPlayer(String player);

        /*
         * @Query("select new  com.ashifs.model.PlayerAndAttribute(b.bowler, b.matchId )  FROM com.ashifs.model.BallByBall as b where b.isWicket = 1 AND ((b.dismissalKind!='RUN OUT') OR (b.dismissalKind='RUN OUT' AND b.fielder = bowler)) AND b.bowlingTeam!='NA' AND b.bowler = :player GROUP BY b.bowler,b.matchId order by b.wickets desc fetch first 1 rows only"
         * ) PlayerAndAttribute findMatchIdMaxWicketsofPlayer(String player);
         */
        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.batsman, SUM(b.batsmanRuns))  FROM com.ashifs.model.BallByBall as b where b.batsman=:player and b.extrasType='NA'")
        PlayerAndAttribute findTotalRunsofPlayer(String player);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.fielder, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.fielder=:player and b.dismissalKind = 'RUN OUT'")
        PlayerAndAttribute findTotalRunOutsOfPlayer(String player);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.fielder, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.fielder=:player and b.dismissalKind = 'STUMPED'")
        PlayerAndAttribute findTotalStumpingsOfPlayer(String player);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.fielder, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.fielder=:player and dismissalKind = 'CAUGHT'")
        PlayerAndAttribute findTotalCatchesOfPlayer(String player);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.batsman, count(*))  FROM com.ashifs.model.BallByBall as b where b.batsman= :player  and batsman_runs=6")
        PlayerAndAttribute findTotalSixesOfPlayer(String player);

        @Query("select new  com.ashifs.pojos.PlayerAndAttribute(b.batsman, count(*))  FROM com.ashifs.model.BallByBall as b where b.batsman= :player  and batsman_runs=4")
        PlayerAndAttribute findTotalFoursOfPlayer(String player);

        @Query(value = " select new com.ashifs.pojos.PlayerAndAttribute(b.batsman,sum(b.batsmanRuns)) "
                        + " FROM com.ashifs.model.BallByBall as b where b.batsman = :player  "
                        + " group by b.matchId having sum(b.batsmanRuns)>=30"
        // + " select distinct b.batsman,0 FROM com.ashifs.model.BallByBall as b where
        // b.batsman = :player)"
        )
        List<PlayerAndAttribute> findTotal30PlusScores(String player);

        @Query(value = " select new com.ashifs.pojos.PlayerAndAttribute(b.batsman,sum(b.batsmanRuns)) "
                        + " FROM com.ashifs.model.BallByBall as b where b.batsman = :player  "
                        + " group by b.matchId having sum(b.batsmanRuns)>=50 and sum(b.batsmanRuns)<100"
        // + " select distinct b.batsman,0 FROM com.ashifs.model.BallByBall as b where
        // b.batsman = :player)"
        )
        List<PlayerAndAttribute> findTotal50PlusScores(String player);

        @Query(value = " select new com.ashifs.pojos.PlayerAndAttribute(b.batsman,sum(b.batsmanRuns)) "
                        + " FROM com.ashifs.model.BallByBall as b where b.batsman = :player  "
                        + " group by b.matchId having sum(b.batsmanRuns)>=100"
        // + " select distinct b.batsman,0 FROM com.ashifs.model.BallByBall as b where
        // b.batsman = :player)"
        )
        List<PlayerAndAttribute> findTotal100PlusScores(String player);

        @Query("SELECT new com.ashifs.pojos.MatchInningScore(b.battingTeam,b.inning,sum(totalRuns),sum(b.isWicket),max(b.over)) "
                        + " FROM com.ashifs.model.BallByBall as b "
                        + " where b.matchId=:matchId group by b.inning,b.battingTeam")
        List<MatchInningScore> findMatchScoreSummaryByMatchId(Long matchId);

        default List<BallByBall> findByMatchIdOrderByInningAndOverAndBallAsc(Long matchId) {
                Sort sort = Sort.by(Sort.Order.asc("over"), Sort.Order.asc("ball"));
                List<BallByBall> ballByBalls = findByMatchIdOrderByInningAsc(matchId, sort);
                return ballByBalls;

        }

}
