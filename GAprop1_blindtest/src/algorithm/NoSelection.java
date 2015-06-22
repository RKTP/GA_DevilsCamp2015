package algorithm;

public class NoSelection extends Gene {
	public NoSelection() {
		gene.removeAllElements();
		for(int index=0;index<data.getNumFeature();index++) {
			gene.add(index);
		}
		finalAccuracy(Generation.dataSet);
	}
	
	public void showAccuracy() {
		System.out.println("Performance without Feature Selection : ");
		System.out.println(this.accuracy + " | " + this.sensitivity + " | " + this.specificity);
		saveResult();
	}
	
	private void saveResult() {
		OutputFile out = OutputFile.getInstance();
		out.writeNFResult(accuracy, sensitivity, specificity);
	}
}