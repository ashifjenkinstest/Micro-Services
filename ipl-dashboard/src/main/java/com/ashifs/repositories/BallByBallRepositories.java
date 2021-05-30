package com.ashifs.repositories;

import java.util.List;

import com.ashifs.model.BallByBall;
import com.ashifs.model.PlayerAndAttribute;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BallByBallRepositories extends JpaRepository<BallByBall, Long> {

    List<BallByBall> findByMatchIdOrderByInningAsc(Long matchId, Sort sort);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.bowler, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.isWicket = 1 AND ((b.dismissalKind!='RUN OUT') OR (b.dismissalKind='RUN OUT' AND b.fielder = bowler)) AND b.bowlingTeam!='NA' AND b.bowler = :player GROUP BY b.bowler")
    PlayerAndAttribute findTotalWicketsofPlayer(String player);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.batsman, SUM(b.batsmanRuns))  FROM com.ashifs.model.BallByBall as b where b.batsman=:player and b.extrasType='NA'")
    PlayerAndAttribute findTotalRunsofPlayer(String player);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.fielder, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.fielder=:player and b.dismissalKind = 'RUN OUT'")
    PlayerAndAttribute findTotalRunOutsOfPlayer(String player);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.fielder, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.fielder=:player and b.dismissalKind = 'STUMPED'")
    PlayerAndAttribute findTotalStumpingsOfPlayer(String player);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.fielder, SUM(b.isWicket))  FROM com.ashifs.model.BallByBall as b where b.fielder=:player and dismissalKind = 'CAUGHT'")
    PlayerAndAttribute findTotalCatchesOfPlayer(String player);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.batsman, count(*))  FROM com.ashifs.model.BallByBall as b where b.batsman= :player  and batsman_runs=6")
    PlayerAndAttribute findTotalSeixesOfPlayer(String player);

    @Query("select new  com.ashifs.model.PlayerAndAttribute(b.batsman, count(*))  FROM com.ashifs.model.BallByBall as b where b.batsman= :player  and batsman_runs=4")
    PlayerAndAttribute findTotalFoursOfPlayer(String player);

    default List<BallByBall> findByMatchIdOrderByInningAndOverAndBallAsc(Long matchId) {
        Sort sort = Sort.by(Sort.Order.asc("over"), Sort.Order.asc("ball"));
        List<BallByBall> ballByBalls = findByMatchIdOrderByInningAsc(matchId, sort);
        return ballByBalls;

    }

}
