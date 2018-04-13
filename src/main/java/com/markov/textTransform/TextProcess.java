package com.markov.textTransform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class does the process in the input text file
 * 
 * @author Reza.sh
 *
 */
public class TextProcess {

	public static final Logger logger = LoggerFactory.getLogger(TextProcess.class);
	
	/**
	 * This method returns the output transformed string
	 * 
	 * @param textInfo
	 * @return String
	 * @throws IOException
	 */
	public String markovTransform(TextInfo textInfo) throws Exception {

		try {
			Random r = new Random();
			Map<String, List<String>> dict = generatePrefixes(textInfo);

			// we can send the prefix from user
			// for now the prefix is a random String from the map
			String prefix = getPrefix(dict);

			List<String> output = new ArrayList<String>(Arrays.asList(prefix.split(" ")));
			int outputSize = textInfo.getOutputSize();
			int n = 0;
			while (true) {
				List<String> suffix = dict.get(prefix);
				if (suffix.size() == 1) {
					if (Objects.equals(suffix.get(0), ""))
						return output.stream().reduce("", (a, b) -> a + " " + b);
					output.add(suffix.get(0));
				} else {
					int rn = r.nextInt(suffix.size());
					output.add(suffix.get(rn));
				}
				if (output.size() >= outputSize)
					return output.stream().limit(outputSize).reduce("", (a, b) -> a + " " + b);
				n++;
				prefix = output.stream().skip(n).limit(textInfo.getKeySize()).reduce("", (a, b) -> a + " " + b).trim();
			}
		} catch (Exception exc) {
			logger.error(exc.getMessage());
			throw exc;
		}

	}

	/**
	 * This method returns the Prefixes based on the input parameters
	 * 
	 * @param words
	 * @param keySize
	 * @return Map<String, List<String>>
	 * @throws IOException
	 */
	public Map<String, List<String>> generatePrefixes(TextInfo textInfo) throws IOException {

		String[] words = TextUtils.getWordsOfFile(textInfo.getPath());
		Map<String, List<String>> dict = new HashMap<String, List<String>>();

		int keySize = textInfo.getKeySize();

		for (int i = 0; i < (words.length - keySize); ++i) {
			StringBuilder key = new StringBuilder(words[i]);
			for (int j = i + 1; j < i + keySize; ++j) {
				key.append(' ').append(words[j]);
			}
			String value = (i + keySize < words.length) ? words[i + keySize] : "";
			if (!dict.containsKey(key.toString())) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(value);
				dict.put(key.toString(), list);
			} else {
				dict.get(key.toString()).add(value);
			}
		}

		return dict;
	}
	
	/**
	 * This method return a random prefix from the list of prefixes
	 * it can be selected by user in real application (like designed UI)
	 * @param dict
	 * @return String
	 */
	protected String getPrefix(Map<String, List<String>> dict) {
		Random r = new Random();
		int rn = r.nextInt(dict.size());
		return (String) dict.keySet().toArray()[rn];
	}

}
