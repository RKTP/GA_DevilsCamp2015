package algorithm;
import java.io.*;

public class OutputFile {
	private static OutputFile instance = new OutputFile();
	private File output;
	private FileWriter writer;
	private BufferedWriter out;
	
	private OutputFile() {
		output = new File("result.csv");
		try {
			output.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			writer = new FileWriter(output);
			out = new BufferedWriter(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeResult(double acc, double sensitivity, double specificity) {
		try {
			out.write(acc + " , " + sensitivity + " , " + specificity);
			out.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void writeNFResult(double acc, double sensitivity, double specificity) {
		try {
			out.write(acc + " , " + sensitivity + " , " + specificity);
			out.newLine();
			out.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static OutputFile getInstance() {
		return instance;
	}
	
	public void closeOutput() {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}