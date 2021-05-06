package com.ashifs.repositories;

import com.ashifs.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team,Long>{


    
    Team findByTeamName(String teamName);
    
}
