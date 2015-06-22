package algorithm;
import java.util.*;

public class DivideSet extends PatternSet {
	private final double testRate = 0.2;
	public PatternSet testSet;
	public PatternSet trainSet;
	private int numofTest;
	private int numofTrain;
	
	public DivideSet() {
		super(InputFile.getInstance());
		initBlindTest();
	}
	
	public DivideSet(PatternSet recent) {
		super(recent);
		initBlindTest();
	}
	
	private void initBlindTest() {
		Vector<Integer> trueValue = new Vector<Integer>();
		Vector<Integer> falseValue = new Vector<Integer>();
		testSet = new PatternSet();
		trainSet = new PatternSet();
		for(int index=0;index<label.size();index++) {
			if(label.elementAt(index)) {
				trueValue.add(index);
			} else {
				falseValue.add(index);
			}
		}
		int targetSize = (int)(trueValue.size()*testRate);
		while(testSet.feature.size()<targetSize) {
			int tIndex = (int)(Math.random()*trueValue.size());
			testSet.feature.add(feature.elementAt(trueValue.elementAt(tIndex)));
			testSet.label.add(label.elementAt(trueValue.elementAt(tIndex)));
			trueValue.remove(tIndex);
		}
		targetSize += (int)(falseValue.size()*testRate);
		while(testSet.feature.size()<targetSize) {
			int tIndex = (int)(Math.random()*falseValue.size());
			testSet.feature.add(feature.elementAt(falseValue.elementAt(tIndex)));
			testSet.label.add(label.elementAt(falseValue.elementAt(tIndex)));
			falseValue.remove(tIndex);
		}
		while(!trueValue.isEmpty()) {
			trainSet.feature.add(feature.elementAt(trueValue.elementAt(0)));
			trainSet.label.add(label.elementAt(trueValue.elementAt(0)));
			trueValue.remove(0);
		}
		while(!falseValue.isEmpty()) {
			trainSet.feature.add(feature.elementAt(falseValue.elementAt(0)));
			trainSet.label.add(label.elementAt(falseValue.elementAt(0)));
			falseValue.remove(0);
		}
		numofTrain=trainSet.label.size();
		numofTest=testSet.label.size();
	}
	
	public void resetDivision() {
		initBlindTest();
	}
	
	public int getNTrain() {
		return numofTrain;
	}
	
	public int getNTest() {
		return numofTest;
	}
}
