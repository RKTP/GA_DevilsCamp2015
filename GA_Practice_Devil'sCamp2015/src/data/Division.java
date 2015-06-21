package data;

import java.util.*;

public class Division {
	private ArrayList<Double[]> attribute;
	private ArrayList<Boolean[]> label;
	private int numofData=0;
	
	public ArrayList<Double[]> getAtt() {
		return this.attribute;
	}
	
	public ArrayList<Boolean[]> getLabel() {
		return this.label;
	}
	
	public int getNum() {
		return this.numofData;
	}
	
	public void setAtt(ArrayList<Double[]> att) {
		this.attribute = att;
	}
	
	public void setLabel(ArrayList<Boolean[]> label) {
		this.label = label;
	}
	
	public void setNum(int num) {
		this.numofData = num;
	}
}
