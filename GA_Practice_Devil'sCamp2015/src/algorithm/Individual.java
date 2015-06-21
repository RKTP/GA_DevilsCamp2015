package algorithm;

import java.util.ArrayList;

public class Individual implements Comparable<Individual> {
	protected ArrayList<Integer> gene;
	private double accuracy;
	private data.Division train;
	
	public Individual() {
		this.gene = new ArrayList<Integer>();
		for(int i=0;i<294;i++) {
			if(Math.random()<0.05) {
				gene.add(i);
			}
		}
		this.train = data.Storage.getStorageInstance().getTrain();
	}
	
	public Individual(Individual papa, Individual mama) {
		
	}
	
	private void setAcc() {
		
	}

	@Override
	public int compareTo(Individual object) {
		if(this.accuracy>object.accuracy) {
			return -1;
		} else if(this.accuracy<object.accuracy) {
			return 1;
		} else {
			if(this.gene.size()<object.gene.size()) {
				return -1;
			} else if(this.gene.size()>object.gene.size()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}