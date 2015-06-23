package algorithm;

import java.util.ArrayList;

public class Individual extends Thread implements Comparable<Individual> {
	protected ArrayList<Integer> gene;
	private double accuracy;
	private data.Division givenData = data.Storage.getStorageInstance().getTrain();
	
	//Initializing the first generation individual - Replacement not necessary
	public Individual() {
		this.gene = new ArrayList<Integer>();
		for(int i=0;i<this.givenData.getAtt().get(0).length;i++) {
			if(Math.random()<0.1) {
				this.gene.add(i);
			}
			while(this.gene.size()>10) {
				this.gene.remove((int)(Math.random()*gene.size()));
			}
		}
//		setAcc();
	}
	
	public void run() {
		setAcc();
	}
//***********************************************************************************************************************//
//Replace this Method with your own designed one
	public Individual(Individual papa, Individual mama) {
		this.gene = new ArrayList<Integer>();
		
		//cross
		this.gene.addAll(papa.getGene());
		this.gene.addAll(mama.getGene());
		
		while(this.gene.size()>10) {
			this.gene.remove((int)(Math.random()*gene.size()));
		}
		
		for(int index=0;index<this.gene.size();index++) {
			for(int cIndex=index+1;cIndex<this.gene.size();cIndex++) {
				if(this.gene.get(index)==this.gene.get(cIndex)) this.gene.remove(cIndex--);
			}
		}
		
		//Mutation
		for(int i=0;i<this.gene.size();i++) {
			double mutateFlag=Math.random();
			int temp = this.gene.get(0);
			if(mutateFlag<0.15) {
				while(this.gene.contains(temp)) {
					temp = (int)(Math.random()*this.givenData.getAtt().get(0).length);
				}
				this.gene.remove(i);
				this.gene.add(i,temp);
			}
		}
		setAcc();
	}
//***********************************************************************************************************************//
	
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
		this.accuracy = accuracy;
	}

//Hamming Loss - XOR rate part
	private double calcAcc(int patternIndex) {
		double acc=0;
		Boolean trainLabel[] = givenData.getLabel().get(patternIndex);
		Boolean predictLabel[];
		
		predictLabel = knnClassify(patternIndex);
		
		int xor=0;
		
		for(int i=0;i<trainLabel.length;i++) {
			if(trainLabel[i]!=predictLabel[i]) {
				xor++;
			}
		}
		
		acc = (double)xor/trainLabel.length;
		
		return 1-acc;
	}
	
//3-NN classification
	private Boolean[] knnClassify(int patternIndex) {
		final int knn=3;
		Boolean prediction[] = new Boolean[this.givenData.getLabel().get(0).length];
		ArrayList<Integer> nearestPattern = new ArrayList<Integer>();
		ArrayList<Double> nearestDistance = new ArrayList<Double>();
		
		for(int i=0;i<knn;i++) {
			nearestDistance.add(Double.MAX_VALUE);
			nearestPattern.add(null);
		}
		
		for(int i=0;i<givenData.getNum();i++) {
			if(i==patternIndex) {
				continue;
			}
			double euclideanDistance=0;
			for(int j=0;j<this.gene.size();j++) {
				int attIndex = this.gene.get(j);
				double linearDistance = this.givenData.getAtt().get(i)[attIndex] - this.givenData.getAtt().get(patternIndex)[attIndex];
				euclideanDistance += Math.pow(linearDistance,2);
			}
			euclideanDistance = Math.sqrt(euclideanDistance);
			
			for(int j=0;j<knn;j++) {
				if(nearestDistance.get(j)>euclideanDistance) {
					nearestDistance.add(j,euclideanDistance);
					nearestDistance.remove(3);
					
					nearestPattern.add(j,i);
					nearestPattern.remove(3);
				}
			}
		}

		for(int i=0;i<this.givenData.getLabel().get(0).length;i++) {
			int nearestLabelSum=0;
			for(int j=0;j<knn;j++) {
				if(this.givenData.getLabel().get(nearestPattern.get(j))[i]) {
					nearestLabelSum++;
				}
			}
			if(nearestLabelSum>knn/2) {
				prediction[i]=true;
			} else {
				prediction[i]=false;
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