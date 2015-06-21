package data;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
	private File trainFile = new File("trainData");
	private File testFile = new File("testData");
	private InputStream fileStream = InputStream.getInstance();
	private Division trainSet;
	private Division testSet;
	
	public Storage() {
		try {
			trainSet = fileStream.setData(trainFile);
			testSet =fileStream.setData(testFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Division getTrain() {
		return trainSet;
	}
	
	public Division getTest() {
		return testSet;
	}
}