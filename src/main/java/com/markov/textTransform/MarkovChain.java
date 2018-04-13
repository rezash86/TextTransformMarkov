package com.markov.textTransform;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkovChain {

	public static final Logger logger = LoggerFactory.getLogger(MarkovChain.class);
	
	// The beginning of the project
	public static void main(String[] args) {

		
		
		try {
			TextInfo input = TextUtils.getInput();
			if(input != null) {
			
				TextProcess process = new TextProcess();
				String markovTransform = process.markovTransform(input);
				
				//The result can be send to the client from server to show in the result text box
				System.out.println(markovTransform);
				
			} 
		}catch (IOException exc) {
			logger.error(exc.getMessage());
		} catch (Exception exc) {
			logger.error(exc.getMessage());
		}
	}
}