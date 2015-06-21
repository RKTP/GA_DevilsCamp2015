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
	}
	
	public void run() {
		for(int i=0;i<this.maxGen;i++) {
			
		}
	}
}
