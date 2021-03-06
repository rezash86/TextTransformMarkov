package com.markov.textTransform.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.markov.textTransform.model.TextInfo;
import com.markov.textTransform.process.TextProcess;


@RunWith(MockitoJUnitRunner.class)
public class TextProcessMicroTest {
	
	@Mock
	private TextProcess testClass;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testClass = spy(new TextProcess());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void markovTransform() throws Exception {
		createFile();
		TextInfo textInfo = new TextInfo("newfile.txt", 2, 10);
		String expectedResult = " was beginning to get very tired of sitting by her";

		doReturn("was beginning").when(testClass).getPrefix(any(Map.class));
		String markovTransform = testClass.markovTransform(textInfo) ;
       
		assertEquals(markovTransform, expectedResult);
	}
	
	@Test
	public void generatePrefixes() throws IOException {
		createFile();
		Map<String, List<String>> generatePrefixes = testClass.generatePrefixes(new TextInfo("newfile.txt", 2, 10));
	
		assertEquals(generatePrefixes.size(), 13);
		
		ArrayList<String> prefix = new ArrayList<>();
		prefix.add("the");
		assertEquals(generatePrefixes.get("sister on"), prefix);
		
		prefix.remove(0); 
		prefix.add("sitting");
		assertEquals(generatePrefixes.get("tired of"), prefix);
	}
	
	@After
	public void end() throws IOException {
		Files.deleteIfExists(Paths.get("newfile.txt"));
	}
	
	private void createFile() throws IOException {
		File file = new File("newfile.txt");
		file.createNewFile();
    	PrintWriter writer = new PrintWriter(file, "UTF-8");
    	writer.println("Alice was beginning to get very tired of sitting by her sister on the bank");
    	writer.close();
	}
}
