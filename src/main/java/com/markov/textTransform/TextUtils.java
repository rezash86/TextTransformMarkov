package com.markov.textTransform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class is being used for doing some utility actions
 * 
 * @author Reza.sh
 *
 */
public class TextUtils {

	/**
	 * This method returs all the words in a text file
	 * 
	 * @param filePath
	 * @return String[]
	 * @throws IOException
	 */
	public static String[] getWordsOfFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		byte[] bytes = Files.readAllBytes(path);
		String[] words = new String(bytes).trim().split(" ");
		return words;

	}

	/**
	 * This method returns an object of the TextInfo based on the input parameters
	 * if its null means there is an error in the input parameters
	 * @return TextInfo
	 */
	public static TextInfo getInput() {
		Scanner reader = new Scanner(System.in);
		TextInfo textInfo = new TextInfo();

		try {

			System.out.println("Enter the path of your text file");
			String path = reader.nextLine();
			ValidationInput.getInstance().validatePath(path);

			System.out.println("Enter the size of the key : ");
			int keySize = reader.nextInt();

			ValidationInput.getInstance().validateKeySize(keySize);

			System.out.println("Enter the size of the output : ");
			int outputSize = reader.nextInt();
			textInfo.setKeySize(keySize);
			textInfo.setOutputSize(outputSize);
			textInfo.setPath(path);

			ValidationInput.getInstance().validateOutputSize(textInfo);
			return textInfo;
		}

		catch (Exception exc) {
			System.out.println(exc.getMessage());
		}

		finally {
			reader.close();
		}

		return null;

	}
}
