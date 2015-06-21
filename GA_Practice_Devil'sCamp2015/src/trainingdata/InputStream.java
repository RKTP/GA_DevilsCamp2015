package trainingdata;

import java.io.File;
import java.util.ArrayList;

public class InputStream {
	private static InputStream instance = new InputStream();
	
	private InputStream() {
		
	}
	
	public Division setData(File file) {
		Division dataSet = new Division();
		
		ArrayList<Double> att = new ArrayList<Double>();
		ArrayList<Boolean> label = new ArrayList<Boolean>();
		
		return dataSet;
	}
	
	public static InputStream getInstance() {
		return instance;
	}
}
