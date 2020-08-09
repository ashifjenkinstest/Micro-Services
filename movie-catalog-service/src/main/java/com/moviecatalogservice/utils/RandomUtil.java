package com.moviecatalogservice.utils;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RandomUtil {
	
	
	public int getRandomRating(int range) {
		Random rand = new Random(); 
		  
        // Generate random integers in range 0 to 999 
        int rand_int = rand.nextInt(range) ;
  
        return rand_int;
        
	}

}
