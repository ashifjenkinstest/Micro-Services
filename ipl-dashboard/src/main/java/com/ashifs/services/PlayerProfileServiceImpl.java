package com.ashifs.services;

import javax.transaction.Transactional;

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
    private MatchRepository matchRepository;

    public PlayerProfile getPlayerProfile(String playerName) {

        PlayerProfile playerProfile = new PlayerProfile(playerName, (long) 0, (long) 0, (long) 0, (long) 0, (long) 0,
                (long) 0);
        PlayerAndAttribute wicketsAttribute = ballByBallRepositories.findTotalWicketsofPlayer(playerName);
        PlayerAndAttribute runsAttribute = ballByBallRepositories.findTotalRunsofPlayer(playerName);
        PlayerAndAttribute catchesAttribute = ballByBallRepositories.findTotalCatchessOfPlayer(playerName);
        PlayerAndAttribute stumpingsAttribute = ballByBallRepositories.findTotalStumpingsOfPlayer(playerName);
        PlayerAndAttribute runoutsAttribute = ballByBallRepositories.findTotalRunOutsOfPlayer(playerName);
        PlayerAndAttribute momAttribute = matchRepository.findNoOfMOMByPlayer(playerName);

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
        System.out.println(playerProfile);
        return playerProfile;

    }

}
