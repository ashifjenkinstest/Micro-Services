package com.ashifs.batchitemprocessor;

import java.time.LocalDate;
import java.util.Arrays;

import com.ashifs.data.MatchInput;
import com.ashifs.model.Match;

import org.springframework.batch.item.ItemProcessor;

import antlr.collections.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MatchInputItemProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchInputItemProcessor.class);

    
    @Override
    public Match process(MatchInput matchInput) throws Exception {
        
        Long id = 1L;
        try {
          id = Long.parseLong(matchInput.getId());
          System.out.println(id);
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
        final String city = matchInput.getCity();
        final java.util.List<String> dateFormats = Arrays.asList("yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd");    
        final LocalDate date =LocalDate.parse(matchInput.getDate());
        final String playerOfMatch = matchInput.getPlayer_of_match();
        final String venue = matchInput.getVenue();
        //final String neutralVenue = matchInput.getNeutral_venue();
        //final String team1 = matchInput.getTeam1();
        //final String team2 = matchInput.getTeam2();
        final String tossWinner = matchInput.getToss_winner();
        final String tossDecision = matchInput.getToss_decision();
        final String winner = matchInput.getWinner();
        final String result = matchInput.getResult();
        final String resutMargin = matchInput.getResult_margin();
        final String eliminator = matchInput.getEliminator();
        //final String method = matchInput.getMethod();
        final String umpire1 = matchInput.getUmpire1();
        final String umpire2 = matchInput.getUmpire2();

        String firstInningTeam = null ,secondInningTeam = null;
        

        if("bat".equals(matchInput.getToss_decision())){
        	System.out.println();
          firstInningTeam = matchInput.getToss_winner();
          secondInningTeam = (firstInningTeam).equals(matchInput.getTeam1()) ? matchInput.getTeam2():matchInput.getTeam1();
        }
        else if("field".equals(matchInput.getToss_decision())){
          secondInningTeam = matchInput.getToss_winner();
          firstInningTeam  = (secondInningTeam).equals(matchInput.getTeam1()) ? matchInput.getTeam2():matchInput.getTeam1();
        }
        else {
        	
        }
        
        final Match transformedMatch = new Match(id, city, date, playerOfMatch, venue, firstInningTeam, secondInningTeam, tossWinner, tossDecision,
                             winner, result, resutMargin, eliminator, umpire2, umpire1);
        log.info("Converting (" + matchInput.toString() + ") into (" + transformedMatch.toString() + ")");                             
        return transformedMatch ;
    }
    
}
