package trainingdata;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
	File trainFile = new File("trainData");
	File testFile = new File("testData");
	InputStream fileStream = InputStream.getInstance();
	
	Division trainSet = fileStream.setData(trainFile);
	Division testSet =fileStream.setData(testFile);

}
