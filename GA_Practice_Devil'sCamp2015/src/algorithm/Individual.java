package algorithm;

import java.util.ArrayList;

public class Individual implements Comparable<Individual> {
	protected ArrayList<Integer> gene;
	private double accuracy;
	private data.Division givenData = data.Storage.getStorageInstance().getTrain();
	
	//Initializing the first generation individual - Replacement not necessary
	public Individual() {
		this.gene = new ArrayList<Integer>();
		for(int i=0;i<this.givenData.getAtt().get(0).length;i++) {
			if(Math.random()<0.05) {
				this.gene.add(i);
			}
			while(this.gene.size()>15) {
				this.gene.remove((int)(Math.random()*gene.size()));
			}
		}
		setAcc();
	}

	//Replace the Method
	public Individual(Individual papa, Individual mama) {
		this.gene = new ArrayList<Integer>();
		
		//cross
		this.gene.addAll(papa.getGene());
		this.gene.addAll(mama.getGene());
		
		while(this.gene.size()>15) {
			this.gene.remove((int)(Math.random()*gene.size()));
		}
		
		//Mutation
		for(int i=0;i<this.gene.size();i++) {
			double mutateFlag=Math.random();
			int temp = this.gene.get(0);
			if(mutateFlag<0.1) {
				while(this.gene.contains(temp)) {
					temp = (int)(Math.random()*this.givenData.getAtt().get(0).length);
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
		for(int i=0;i<this.gene.size();i++) {
			System.out.print(" " + this.gene.get(i));
		}
		System.out.println(" || " + this.accuracy);
	}
	
	public void showAcc(data.Division testSet) {
		this.givenData = testSet;
		setAcc();
		for(int i=0;i<this.gene.size();i++) {
			System.out.print(" " + this.gene.get(i));
		}
		System.out.println(" || " + this.accuracy);
	}

//MLA using Hamming Loss
	private void setAcc() {
		double accuracy=0;
		for(int i=0;i<this.givenData.getNum();i++) {
			accuracy += calcAcc(i);
		}
		accuracy = accuracy/this.givenData.getNum();
	}

//Hamming Loss - XOR rate part
	private double calcAcc(int instanceIndex) {
		double acc=0;
		Boolean trainLabel[] = givenData.getLabel().get(instanceIndex);
		Boolean predictLabel[];
		
		predictLabel = knnClassify(instanceIndex);
		
		int xor=0;
		
		for(int i=0;i<trainLabel.length;i++) {
			if(trainLabel[i]==predictLabel[i]) {
				xor++;
			}
		}
		
		acc = (double)xor/trainLabel.length;
		
		return 1-acc;
	}
	
//3-NN classification
	private Boolean[] knnClassify(int instanceIndex) {
		final int knn=3;
		Boolean prediction[] = new Boolean[this.givenData.getLabel().get(0).length];
		int nearest[] = new int[knn];
		double eDistance[] = new double[knn];
		
		for(int i=0;i<knn;i++) {
			eDistance[i]=Double.MAX_VALUE;
		}
		
		for(int i=0;i<givenData.getNum();i++) {
			if(i==instanceIndex) {
				continue;
			}
			for(int j=0;j<givenData.getAtt().get(0).length;j++) {
				
			}
		}
		
		return prediction;
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