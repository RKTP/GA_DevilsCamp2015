package data;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
	private static Storage instance = new Storage();
	private File trainFile = new File("train_arcene.dat");
	private File testFile = new File("valid_arcene.dat");
	private InputStream fileStream = InputStream.getInstance();
	private Division trainSet;
	private Division testSet;
	
	private Storage() {
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
	
	public static Storage getStorageInstance() {
		return instance;
	}
}
