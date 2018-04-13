package com.markov.textTransform;

import java.io.File;
import java.io.IOException;

/**
 * This class is used for validation of the input 
 * 1- path of the text file
 * 2- size of the key
 * 3- size of the output
 * 
 * @author Reza.sh
 *
 */
public class ValidationInput {

	public static ValidationInput instance;

	private ValidationInput() {
	}
	
	/**
	 * Lazy loading of singleton
	 * @return ValidationInput
	 */
	public static ValidationInput getInstance() {
		if (instance == null) {
			instance = new ValidationInput();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param textInfo
	 * @return TRUE if all the validations are TRUE
	 * @throws IOException
	 * @throws Exception
	 */
	public boolean validateTextInfo(TextInfo textInfo) throws IOException, Exception {
		return validatePath(textInfo.getPath()) && validateKeySize(textInfo.getKeySize()) && 
				validateOutputSize(textInfo);
	}
	
	/**
	 * 
	 * @param path
	 * @return TRUE if the file exists
	 * @throws Exception
	 */
	public boolean validatePath(String path) throws Exception {
		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		throw new Exception("File not exists");
	}
	
	/**
	 * 
	 * @param keySize
	 * @return TRUE if the size of the key > 1
	 */
	public boolean validateKeySize(int keySize) {
		if (keySize < 1) {
			throw new IllegalArgumentException("Key size can't be less than 1");
		}
		return true;
	}
	
	/**
	 * 
	 * @param textInfo
	 * @return true if the size is in the range
	 * @throws IOException
	 */
	public boolean validateOutputSize(TextInfo textInfo) throws IOException {
		String[] words = TextUtils.getWordsOfFile(textInfo.getPath());

		if (textInfo.getOutputSize() < textInfo.getKeySize() || textInfo.getOutputSize() >= words.length) {
			throw new IllegalArgumentException("Output size is out of range");
		}
		return true;
	}

}
