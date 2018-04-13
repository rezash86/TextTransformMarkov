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
	 * @param fileInfo
	 * @return TRUE if all the validations are TRUE
	 * @throws IOException
	 * @throws Exception
	 */
	public boolean validateFileInfo(TextInfo fileInfo) throws IOException, Exception {
		return validatePath(fileInfo.getPath()) && validateKeySize(fileInfo.getKeySize()) && 
				validateOutputSize(fileInfo);
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
	 * @param fileInfo
	 * @return true if the size is in the range
	 * @throws IOException
	 */
	public boolean validateOutputSize(TextInfo fileInfo) throws IOException {
		String[] words = FileUtils.getWordsOfFile(fileInfo.getPath());

		if (fileInfo.getOutputSize() < fileInfo.getKeySize() || fileInfo.getOutputSize() >= words.length) {
			throw new IllegalArgumentException("Output size is out of range");
		}
		return true;
	}

}
