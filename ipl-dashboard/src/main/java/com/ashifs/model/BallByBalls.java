package com.ashifs.model;

import java.util.List;

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
