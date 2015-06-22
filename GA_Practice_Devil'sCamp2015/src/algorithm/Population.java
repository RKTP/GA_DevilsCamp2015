package algorithm;

import java.util.ArrayList;

public class Population {
	private ArrayList<Individual> generation;
	private Individual noSelection;
	private int maxGen=100;
	private final int maxPopulation=60;
	private final int supremeNum=10;
	private data.Division test = data.Storage.getStorageInstance().getTest();
	
	public Population() {
		generation = new ArrayList<Individual>();
	}
	
	public Population(int max) {
		System.out.print("Initial population setup...");
		this.maxGen = max;
		generation = new ArrayList<Individual>();
		System.out.println("Done");
	}
	
	public void run() {
		System.out.println("Algorithm boot...");
		initGeneration();
		System.out.println("Algorithm initiated");
		for(int i=1;i<=this.maxGen;i++) {
			System.out.println("Generation " + i);
			showGeneration();
			if(i<this.maxGen)
				nextGeneration();
		}
		showResult();
	}
	
	private void initGeneration() {
		System.out.println("Generating first generation...");
		for(int i=0;i<maxPopulation;i++) {
			generation.add(new Individual());
		}
		System.out.println("Successfully Generated");
		this.generation.sort(null);
	}
	
	private void showResult() {
		System.out.println("Final Result");
		this.generation.get(0).showAcc(this.test);
	}
	
	private void showGeneration() {
		for(int i=0;i<supremeNum;i++) {
			System.out.print(i + ":");
			this.generation.get(i).showAcc();
		}
		System.out.println();
	}
	
	private void nextGeneration() {
		while(generation.size()>supremeNum) {
			this.generation.remove(supremeNum);
		}
		while(generation.size()<maxPopulation)
		this.generation.sort(null);
	}
}
