package algorithm;

import java.util.ArrayList;

public class Population {
	private ArrayList<Individual> generation;
	private Individual noSelection;
	private int maxGen=100;
	private final int maxPopulation=60;
	
	
	private data.Division test = data.Storage.getStorageInstance().getTest();
	
	public Population(int max) {
		this.maxGen = max;
		generation = new ArrayList<Individual>();
	}
	
	public void run() {
		initGeneration();
		System.out.println("Algorithm initiated");
		for(int i=0;i<this.maxGen;i++) {
			nextGeneration();
			System.out.println("Generation " + i);
		}
	}
	
	private void initGeneration() {
		for(int i=0;i<maxPopulation;i++) {
			generation.add(new Individual());
		}
	}
	
	private void nextGeneration() {
		
	}
}
