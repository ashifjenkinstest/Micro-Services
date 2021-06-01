package com.ashifs.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ashifs.model.BestBowlingFigure;
import com.ashifs.model.BowlerAttrs;
import com.ashifs.model.Match;
import com.ashifs.model.MatchInningScore;
import com.ashifs.model.MatchInningsDetails;
import com.ashifs.model.MatchScoreAndSummary;
import com.ashifs.model.PlayerAndAttribute;
import com.ashifs.model.PlayerProfile;
import com.ashifs.repositories.BallByBallRepositories;
import com.ashifs.repositories.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerProfileServiceImpl implements PlayerProfileService {

    @Autowired
    private BallByBallRepositories ballByBallRepositories;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MatchRepository matchRepository;

    public PlayerProfile getPlayerProfile(String playerName) {

        PlayerProfile playerProfile = new PlayerProfile(playerName, (long) 0, (long) 0, (long) 0, (long) 0, (long) 0,
                (long) 0, (long) 0, (long) 0, (long) 0, (long) 0, (long) 0, (long) 0, (long) 0, (long) 0);
        PlayerAndAttribute wicketsAttribute = ballByBallRepositories.findTotalWicketsofPlayer(playerName);
        PlayerAndAttribute runsAttribute = ballByBallRepositories.findTotalRunsofPlayer(playerName);
        PlayerAndAttribute catchesAttribute = ballByBallRepositories.findTotalCatchesOfPlayer(playerName);
        PlayerAndAttribute stumpingsAttribute = ballByBallRepositories.findTotalStumpingsOfPlayer(playerName);
        PlayerAndAttribute runoutsAttribute = ballByBallRepositories.findTotalRunOutsOfPlayer(playerName);
        PlayerAndAttribute momAttribute = matchRepository.findNoOfMOMByPlayer(playerName);
        PlayerAndAttribute sixesAttribute = ballByBallRepositories.findTotalSixesOfPlayer(playerName);
        PlayerAndAttribute foursAttribute = ballByBallRepositories.findTotalFoursOfPlayer(playerName);
        // System.out.println(ballByBallRepositories.findTotal30PlusScores(playerName).getClass());

        PlayerAndAttribute thirtyPlusRunsAttribute = null;
        List<PlayerAndAttribute> thitryPlusRunsAttributes = ballByBallRepositories.findTotal30PlusScores(playerName);
        if (thitryPlusRunsAttributes != null) {

            thirtyPlusRunsAttribute = new PlayerAndAttribute();
            thirtyPlusRunsAttribute.setAttr((long) thitryPlusRunsAttributes.size());
            thirtyPlusRunsAttribute.setPlayer(playerName);
            // System.out.println(thirtyPlusRunsAttribute.toString());

        }

        PlayerAndAttribute fiftyPlusRunsAttribute = null;
        List<PlayerAndAttribute> fiftyPlusRunsAttributes = ballByBallRepositories.findTotal50PlusScores(playerName);
        if (thitryPlusRunsAttributes != null) {

            fiftyPlusRunsAttribute = new PlayerAndAttribute();
            fiftyPlusRunsAttribute.setAttr((long) fiftyPlusRunsAttributes.size());
            fiftyPlusRunsAttribute.setPlayer(playerName);
            // System.out.println(fiftyPlusRunsAttribute.toString());

        }

        PlayerAndAttribute hundredPlusRunsAttribute = null;
        List<PlayerAndAttribute> hundredPlusRunsAttributes = ballByBallRepositories.findTotal100PlusScores(playerName);
        if (thitryPlusRunsAttributes != null) {

            hundredPlusRunsAttribute = new PlayerAndAttribute();
            hundredPlusRunsAttribute.setAttr((long) hundredPlusRunsAttributes.size());
            hundredPlusRunsAttribute.setPlayer(playerName);
            // System.out.println(hundredPlusRunsAttribute.toString());

        }

        //
        if (wicketsAttribute != null && playerName.equals(wicketsAttribute.getPlayer()))
            playerProfile.setWickets(wicketsAttribute.getAttr());
        if (runsAttribute != null && playerName.equals(runsAttribute.getPlayer()))
            playerProfile.setRuns(runsAttribute.getAttr());
        if (catchesAttribute != null && playerName.equals(catchesAttribute.getPlayer()))
            playerProfile.setCatches(catchesAttribute.getAttr());
        if (stumpingsAttribute != null && playerName.equals(stumpingsAttribute.getPlayer()))
            playerProfile.setStumps(stumpingsAttribute.getAttr());
        if (runoutsAttribute != null && playerName.equals(runoutsAttribute.getPlayer()))
            playerProfile.setRunOuts(runoutsAttribute.getAttr());
        if (momAttribute != null && playerName.equals(momAttribute.getPlayer()))
            playerProfile.setMom(momAttribute.getAttr());
        if (sixesAttribute != null && playerName.equals(sixesAttribute.getPlayer()))
            playerProfile.setSixes(sixesAttribute.getAttr());
        if (foursAttribute != null && playerName.equals(foursAttribute.getPlayer()))
            playerProfile.setFours(foursAttribute.getAttr());
        if (thirtyPlusRunsAttribute != null && playerName.equals(thirtyPlusRunsAttribute.getPlayer()))
            playerProfile.setThirtyPlusRunsInMatch(thirtyPlusRunsAttribute.getAttr());
        if (fiftyPlusRunsAttribute != null && playerName.equals(fiftyPlusRunsAttribute.getPlayer()))
            playerProfile.setFiftyPlusRunsInMatch(fiftyPlusRunsAttribute.getAttr());
        if (hundredPlusRunsAttribute != null && playerName.equals(hundredPlusRunsAttribute.getPlayer()))
            playerProfile.setHundredPlusRunsInMatch(hundredPlusRunsAttribute.getAttr());

        /* Old Logic to get best bowling figure */
        /*
         * BowlerAttrs bowlerAttrs = new BowlerAttrs(playerName, (long) 0, (long) 0);
         * for (BowlerAttrs bowlerAttrsTemp :
         * ballByBallRepositories.findMaxWicketsAndMatchIdofPlayer(playerName)) { if
         * (bowlerAttrsTemp.getWicketsOrRuns() > bowlerAttrs.getWicketsOrRuns()) {
         * bowlerAttrs.setMatchId(bowlerAttrsTemp.getMatchId());
         * bowlerAttrs.setWicketsOrRuns(bowlerAttrsTemp.getWicketsOrRuns()); } }
         * System.out.println(bowlerAttrs); System.out.println(ballByBallRepositories.
         * findTotalRunsInHighestWicketMatchofPlayer(bowlerAttrs.getBowlerName(),
         * bowlerAttrs.getMatchId()));
         * 
         */
        BestBowlingFigure bestBowlingFigure = new BestBowlingFigure(playerName, (long) 0, (long) 0);
        ballByBallRepositories.findBestBowlingFigurefPlayer(playerName);

        for (BestBowlingFigure playerAndAttributeTemp : ballByBallRepositories
                .findBestBowlingFigurefPlayer(playerName)) {
            if (playerAndAttributeTemp.getWickets() >= bestBowlingFigure.getWickets()) {
                bestBowlingFigure.setWickets(playerAndAttributeTemp.getWickets());
                bestBowlingFigure.setRuns(playerAndAttributeTemp.getRuns());
            }
        }
        System.out.println(bestBowlingFigure);
        if (bestBowlingFigure != null && playerName.equals(bestBowlingFigure.getBowler()))
            playerProfile.setBestBowlingFigureWickets(bestBowlingFigure.getWickets());
        playerProfile.setBestBowlingFigureRuns(bestBowlingFigure.getRuns());

        return playerProfile;

    }

    public MatchScoreAndSummary getMatchScoreAndSummaryByMatchId(Long matchId) {

        MatchScoreAndSummary matchScoreAndSummary = new MatchScoreAndSummary();

        Optional<Match> matchs = matchRepository.findById(matchId);
        if (matchs.isPresent()) {
            System.out.println("Match Present");
            matchScoreAndSummary.setMatch(matchs.get());
        } else {
            System.out.println("Match Not Present");
            matchScoreAndSummary.setMatch(new Match());
        }

        MatchInningsDetails matchInningsDetails = new MatchInningsDetails();
        List<MatchInningScore> inningScores = ballByBallRepositories.findMatchScoreSummaryByMatchId(matchId);
        for (MatchInningScore matchInningScore : inningScores) {
            if (matchInningScore.getInning() == (1)) {
                matchInningsDetails.setFirstInningOvers(matchInningScore.getOvers());
                matchInningsDetails.setFirstInningTeam(matchInningScore.getTeam());
                matchInningsDetails.setFirstInningRuns(matchInningScore.getRuns());
                matchInningsDetails.setFirstInningWickets(matchInningScore.getWickets());
            }
            if (matchInningScore.getInning() == (2)) {
                matchInningsDetails.setSecondInningOvers(matchInningScore.getOvers());
                matchInningsDetails.setSecondInningTeam(matchInningScore.getTeam());
                matchInningsDetails.setSecondInningRuns(matchInningScore.getRuns());
                matchInningsDetails.setSecondInningWickets(matchInningScore.getWickets());
            }

        }
        matchScoreAndSummary.setMatchInningsScore(matchInningsDetails);
        return matchScoreAndSummary;
    }

}
