package algorithm;
import java.util.*;

public class Gene extends Thread implements Comparable<Gene> {
	protected Vector<Integer> gene;
	protected DivideSet data = new DivideSet(Generation.dataSet.trainSet);
	protected double accuracy;
	protected double sensitivity;
	protected double specificity;
	private final int testCount = 30;
	
	@Override
	public int compareTo(Gene object) {
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
	
//Initializing first Generation
	public Gene() {
		initGen();
//		setAccuracy();
	}
	
//Generating new Generation from current Generation//
	public Gene(Vector<Integer> fGene, Vector<Integer> sGene) {
		crossGene(fGene,sGene);
		mutateGene();
//		setAccuracy();
	}
	
	public void run() {
		setAccuracy();
	}

//***********************************************************************************************************//
//***********************************************************************************************************//
//Randomly generate the gene
	private void initGen() {
		double insertFlag;
		gene = new Vector<Integer>();
		
		for(int index=0;index<data.getNumFeature();index++) {
			insertFlag=Math.random();
			if(insertFlag<0.001) this.gene.add(index);
		}
		while(this.gene.size()>15) {
			this.gene.remove((int)Math.random()*this.gene.size());
		}
	}

//Generate Gene from selected genes//
	private void crossGene(Vector<Integer> fGene,Vector<Integer> sGene) {
		double deleteFlag;
		this.gene = new Vector<Integer>();
		
		this.gene.addAll(fGene);
		this.gene.addAll(sGene);
		if(this.gene.size()>this.data.getNumFeature()/500) {
			for(int index=0;index<this.gene.size();index++) {
				deleteFlag=Math.random();
				if(deleteFlag<0.4) this.gene.remove(index--);
			}
		} else {
			for(int index=0;index<this.gene.size();index++) {
				deleteFlag=Math.random();
				if(deleteFlag<0.1) this.gene.remove(index--);
			}
		}
		for(int index=0;index<this.gene.size();index++) {
			for(int cIndex=index+1;cIndex<this.gene.size();cIndex++) {
				if(this.gene.elementAt(index)==this.gene.elementAt(cIndex)) this.gene.remove(cIndex--);
			}
		}
		while(this.gene.size()>15) {
			this.gene.remove((int)Math.random()*this.gene.size());
		}
	}

//Randomly mutate the gene//
	public void mutateGene() {
		double mutateFlag;
		int tryCount;
		
		for(int index=0;index<this.gene.size();index++) {
			mutateFlag=Math.random();
			tryCount=0;
			if(mutateFlag<0.2) {
				int temp=this.gene.elementAt(0);
				while(this.gene.contains(temp)||tryCount<5000) {
					temp=(int)(this.data.getNumFeature()*Math.random());
					tryCount++;
				}
				if(tryCount<5000) this.gene.add(index, temp);
			}
		}
	}
//***********************************************************************************************************//
//***********************************************************************************************************//
	
//Get Accuracy of the gene//
/*
	protected void setAccuracy() {
		double[] accArr = new double[this.data.getNData()];
		double avgAcc = 0;
		for(int index=0;index<this.data.getNData();index++) {
			accArr[index] = multiLabelAccuracy(index);
			avgAcc += accArr[index]/this.data.getNData();
		}
		this.accuracy = avgAcc;
	}
*/
	protected void setAccuracy() {
		double sumAcc=0;
		for(int index=0;index<testCount;index++) {
			sumAcc+=calcAccuracy();
			data.resetDivision();
		}
		this.accuracy=sumAcc/testCount;
	}

	
	private double calcAccuracy() {
		int sumCorrect=0;
		for(int index=0;index<this.data.getNTest();index++) {
			sumCorrect+=predictionAccuracy(index);
		}
		return (double)sumCorrect/(double)this.data.getNTest();
	}

//Sensitivity, and Specificity
	public void finalAccuracy(DivideSet testData) {
		this.data = testData;
		this.sensitivity=0.0;
		this.specificity=0.0;
		int predictTrue=0,predictFalse=0;
		double sumAcc=0;
		for(int index=0;index<testCount;index++) {
			sumAcc+=calcAccuracy();
			for(int sIndex=0;sIndex<this.data.getNTest();sIndex++) {
				boolean prediction=predict(sIndex);
				if(prediction&&this.data.testSet.label.elementAt(sIndex)) {
					predictTrue++;
				} else if((!prediction&&!this.data.testSet.label.elementAt(sIndex))) {
					predictFalse++;
				}
			}
			this.sensitivity += predictTrue;
			this.specificity += predictFalse;
		}
		this.accuracy=sumAcc/testCount;
		this.sensitivity=sensitivity/testCount;
		this.specificity=specificity/testCount;
/*		this.accuracy=calcAccuracy();
		for(int sIndex=0;sIndex<this.data.getNTest();sIndex++) {
			boolean prediction=predict(sIndex);
			if(prediction&&this.data.testSet.label.elementAt(sIndex)) {
				predictTrue++;
			} else if((!prediction&&!this.data.testSet.label.elementAt(sIndex))) {
				predictFalse++;
			}
		}
		this.sensitivity=predictTrue;
		this.specificity=predictFalse;*/
	}

//MLA//
	private int predictionAccuracy(int dataIndex) {
		boolean prediction;
		prediction = predict(dataIndex);
			if(prediction==data.testSet.label.elementAt(dataIndex)) return 1;
			else return 0;
	}
	
//3-NN for selecting similar data + Predicting label//
	private boolean predict(int dataIndex) {
		boolean prediction;
		double[][] distanceSet = new double[3][2];
		
		for(int index=0;index<3;index++) {
			distanceSet[index][0] = Double.MAX_VALUE;
		}
		
		for(int index=0;index<this.data.getNTrain();index++) {
			double temp=dataDistance(dataIndex,index);
			for(int sIndex=0;sIndex<3;sIndex++) {
				if(distanceSet[sIndex][0]>=temp) {
					for(int tIndex=2;tIndex>sIndex;tIndex--) {
						distanceSet[tIndex][0] = distanceSet[tIndex-1][0];
						distanceSet[tIndex][1] = distanceSet[tIndex-1][1];
					}
					distanceSet[sIndex][0] = temp;
					distanceSet[sIndex][1] = index;
				}
			}
		}
//Prediction from this point//
		int labelFlag=0;
		for(int sIndex=0;sIndex<3;sIndex++) {
			if(data.trainSet.label.elementAt((int)distanceSet[sIndex][1])) {
				labelFlag++;
			} else {
				labelFlag--;
			}
		}
		if(labelFlag>0) {
			prediction=true;
		} else {
			prediction=false;
		}
		return prediction;
	}
	
//Calculate distance between two data for k-NN//
	private double dataDistance(int dataIndex, int compare) {
		double distance;
		double sum=0;
		if(dataIndex==compare)
			return Double.MAX_VALUE;
		
		for(int index=0;index<this.gene.size();index++) {
			double self,comparison;
			self = data.testSet.feature.elementAt(dataIndex).elementAt(this.gene.elementAt(index));
			comparison = data.trainSet.feature.elementAt(compare).elementAt(this.gene.elementAt(index));
			sum += Math.pow(self-comparison, 2.0);
		}
		distance = Math.sqrt(sum);
		return distance;
	}
	
	public Vector<Integer> getGene() {
		return gene;
	}
	
	public double getAccuracy() {
		return accuracy;
	}
	
	public void showAccuracy() {
		System.out.println(this.accuracy + " | " + this.sensitivity + " | " + this.specificity);
		saveResult();
	}
	
	public String showGene() {
		String geneElement="";
		
		this.gene.sort(null);
		for(int index=0;index<gene.size();index++) {
			geneElement+=gene.elementAt(index);
			geneElement+=" ";
		}
		geneElement += "| " + this.accuracy;
		return geneElement;
	}
	
	public void saveOnlyAcc() {
		OutputFile out = OutputFile.getInstance();
		out.writeResult(accuracy, 0.0, 0.0);
	}
	
	private void saveResult() {
		OutputFile out = OutputFile.getInstance();
		out.writeResult(accuracy, sensitivity, specificity);
	}
}
