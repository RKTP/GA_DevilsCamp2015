package algorithm;
import java.util.*;

public class PatternSet {
	public Vector<Vector<Double>> feature=new Vector<Vector<Double>>();
	public Vector<Boolean> label=new Vector<Boolean>();
	
	public PatternSet() {
		
	}
	
	public PatternSet(InputFile data) {
		this.feature.addAll(data.feature);
		this.label.addAll(data.label);
	}
	
	public PatternSet(PatternSet recentSet) {
		feature.addAll(recentSet.feature);
		label.addAll(recentSet.label);
	}
	
	public int getNumData() {
		return label.size();
	}
	
	public int getNumFeature() {
		return feature.elementAt(0).size();
	}
}