package trainingdata;

import java.util.*;

public class Division {
	ArrayList<Double> attribute;
	ArrayList<Boolean> label;
	
	public ArrayList<Double> getAtt() {
		return this.attribute;
	}
	
	public ArrayList<Boolean> getLabel() {
		return this.label;
	}
	
	public void setAtt(ArrayList<Double> att) {
		this.attribute = att;
	}
	
	public void setLabel(ArrayList<Boolean> label) {
		this.label = label;
	}
}
