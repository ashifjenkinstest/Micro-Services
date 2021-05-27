package com.ashifs.batchitemprocessor;

import com.ashifs.data.BallByBallInput;
import com.ashifs.model.BallByBall;

import org.springframework.batch.item.ItemProcessor;

public class BallByBallItemProcessor implements ItemProcessor<BallByBallInput, BallByBall> {

    @Override
    public BallByBall process(BallByBallInput ballByBallInput) throws Exception {

        final Long matchId = Long.parseLong(ballByBallInput.getId());
        final Long inning = Long.parseLong(ballByBallInput.getInning());
        final Long over = Long.parseLong(ballByBallInput.getOver());
        final Long ball = Long.parseLong(ballByBallInput.getBall());
        final String batsman = ballByBallInput.getBatsman();
        final String nonStriker = ballByBallInput.getNonStriker();
        final String bowler = ballByBallInput.getBowler();
        final Long batsmanRuns = Long.parseLong(ballByBallInput.getBatsmanRuns());
        final Long extraRuns = Long.parseLong(ballByBallInput.getExtraRuns());
        final Long totalRuns = Long.parseLong(ballByBallInput.getTotalRuns());
        final Long nonBoundary = Long.parseLong(ballByBallInput.getNonBoundary());
        final Long isWicket = Long.parseLong(ballByBallInput.getIsWicket());
        final String dismissalKind = ballByBallInput.getDismissalKind().toUpperCase();
        final String playerDismissed = ballByBallInput.getPlayerDismissed();
        final String fielder = ballByBallInput.getDismissalKind().toUpperCase().equalsIgnoreCase("BOWLED")
                ? ballByBallInput.getBowler()
                : ballByBallInput.getFielder();
        final String extrasType = ballByBallInput.getExtrasType().toUpperCase();
        final String battingTeam = ballByBallInput.getBattingTeam();
        final String bowlingTeam = ballByBallInput.getBowlingTeam();

        BallByBall ballByBall = new BallByBall(matchId, inning, over, ball, batsman, nonStriker, bowler, batsmanRuns,
                extraRuns, totalRuns, nonBoundary, isWicket, dismissalKind, playerDismissed, fielder, extrasType,
                battingTeam, bowlingTeam);
        return ballByBall;

    }

}
