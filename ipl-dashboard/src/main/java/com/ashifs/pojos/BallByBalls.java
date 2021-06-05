package com.ashifs.pojos;

import java.util.List;

import com.ashifs.model.BallByBall;

public class BallByBalls {

    private List<BallByBall> ballByBallofMatch;

    public BallByBalls(List<BallByBall> ballByBall) {
        this.ballByBallofMatch = ballByBall;
    }

    public List<BallByBall> getBallByBall() {
        return ballByBallofMatch;
    }

    public void setBallByBall(List<BallByBall> ballByBall) {
        this.ballByBallofMatch = ballByBall;
    }

}
