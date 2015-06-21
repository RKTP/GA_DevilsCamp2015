package algorithm;

import java.util.ArrayList;

public class Individual implements Comparable<Individual> {
	protected ArrayList<Integer> gene;
	private double accuracy;
	private data.Division train= data.Storage.getStorageInstance().getTrain();
	
	public Individual() {
		this.gene = new ArrayList<Integer>();
		for(int i=0;i<294;i++) {
			if(Math.random()<0.05) {
				gene.add(i);
			}
		}
		setAcc();
	}
	
	public Individual(Individual papa, Individual mama) {
		this.gene = new ArrayList<Integer>();
		
		this.gene.addAll(papa.getGene());
		this.gene.addAll(mama.getGene());
		
		//Cross
		while(this.gene.size()>15) {
			this.gene.remove((int)(Math.random()*gene.size()));
		}
		
		//Mutation
		for(int i=0;i<this.gene.size();i++) {
			double mutateFlag=Math.random();
			int temp = this.gene.get(0);
			if(mutateFlag<0.1) {
				while(this.gene.contains(temp)) {
					temp = (int)(Math.random()*294);
				}
				this.gene.remove(i);
				this.gene.add(i,temp);
			}
		}
		setAcc();
	}
	
	public ArrayList<Integer> getGene() {
		return this.gene;
	}
	
	public void showAcc() {
		System.out.println("");
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