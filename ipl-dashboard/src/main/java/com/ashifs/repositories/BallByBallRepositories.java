package com.ashifs.repositories;

import java.util.List;

import com.ashifs.model.BallByBall;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallByBallRepositories extends JpaRepository<BallByBall, Long> {

    List<BallByBall> findByMatchIdOrderByInningAsc(Long matchId, Sort sort);

    default List<BallByBall> findByMatchIdOrderByInningAndOverAndBallAsc(Long matchId) {
        Sort sort = Sort.by(Sort.Order.asc("over"), Sort.Order.asc("ball"));
        List<BallByBall> ballByBalls = findByMatchIdOrderByInningAsc(matchId, sort);
        return ballByBalls;

    }

}
