package algorithm;
import java.util.*;
import java.io.*;

public class InputFile {
	private static InputFile dataInstance = new InputFile();
	private File inputFile;

	private final String fileName = "totalData.txt";
	private final int numofFeature = 10000;
	private final int numofData = 200;

	public Vector<Vector<Double>> feature = new Vector<Vector<Double>>();
	public Vector<Boolean> label = new Vector<Boolean>();

	private InputFile() {
		 inputFile = new File(fileName);
		 
		 if(!inputFile.exists()) {
		      System.out.println("Source file " + fileName + " does not exist");
		      System.exit(0);
		 }
		parseData(inputFile);
	}

	private void parseData(File inputFile) {
		String temp;
		Scanner input;
		try {
			input = new Scanner(inputFile);

			for(int index=0;index<numofData;index++) {
				temp = input.nextLine();
				StringTokenizer tok = new StringTokenizer(temp," ");
				Vector<Double> pattern = new Vector<Double>();
				int fCount=0;
				while(tok.hasMoreElements()&&fCount<numofFeature) {
					pattern.add(Double.parseDouble(tok.nextToken()));
					fCount++;
				}
				while(fCount<numofFeature) {
					pattern.add(0.0);
					fCount++;
				}
				feature.add(pattern);
			}

			for(int index=0;index<numofData;index++) {
				temp=input.nextLine();
				if(Integer.parseInt(temp)==1) {
					label.add(true);
				} else {
					label.add(false);
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static InputFile getInstance() {
		return dataInstance;
	}
}
