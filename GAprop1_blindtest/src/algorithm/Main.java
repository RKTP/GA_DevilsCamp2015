package algorithm;

public class Main {

	public static void main(String[] args) {
		final int maxGen = 100;
		for(int i=0;i<30;i++) {
			Generation geneticAlgorithm = new Generation(maxGen);
			geneticAlgorithm.algorithm();
			NoSelection allFeature = new NoSelection();
			allFeature.start();
			try {
				allFeature.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			allFeature.showAccuracy();
		}
		OutputFile.getInstance().closeOutput();
	}
}