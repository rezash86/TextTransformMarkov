package com.markov.textTransform;

import java.io.IOException;

public class MarkovChain {

	// The beginning of the project
	public static void main(String[] args) {
		try {
			TextInfo input = FileUtils.getInput();
			if(input != null) {
			
				FileProcess process = new FileProcess();
				String markovTransform = process.markovTransform(input);
				
				//The result can be send to the client from server to show in the result text box
				System.out.println(markovTransform);
				
			} 
		}catch (IOException exc) {
			System.out.println(exc.getMessage());
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
}