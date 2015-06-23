package algorithm;

import java.util.ArrayList;

public class Population {
	private ArrayList<Individual> generation;
	private Individual noSelection;
	private int maxGen=50;
	private final int maxPopulation=50;
	private final int supremeNum=10;
	private final int numofThread=5;
	private data.Division test = data.Storage.getStorageInstance().getTest();
	
	public Population() {
		System.out.println("Default max Generation : 100");
		System.out.print("Initial population setup...");
		generation = new ArrayList<Individual>();
		System.out.println("Done");
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
		System.out.print("Generating first generation...");
		for(int i=0;i<maxPopulation;i++) {
			generation.add(new Individual());
		}
		
		for(int i=0;i<maxPopulation;i+=numofThread) {
			ArrayList<Individual> thread = new ArrayList<Individual>();
			for(int j=0;j<numofThread;j++) {
				thread.add(this.generation.get(i+j));
			}
			thread.get(0).start();
			thread.get(1).start();
			thread.get(2).start();
			thread.get(3).start();
			thread.get(4).start();			
			try {
				thread.get(0).join();
				thread.get(1).join();
				thread.get(2).join();
				thread.get(3).join();
				thread.get(4).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
			System.out.print(i+1 + ":");
			this.generation.get(i).showAcc();
		}
		System.out.println();
	}

//***********************************************************************************************************************//
//Replace this Method with your own designed one
	private void nextGeneration() {
		while(generation.size()>supremeNum) {
			this.generation.remove(supremeNum);
		}
		while(generation.size()<maxPopulation) {
			int selectionIndex = (int)(Math.random()*supremeNum);
			selectionIndex = selectionIndex%supremeNum;
			Individual papa = this.generation.get(selectionIndex);
			selectionIndex += (int)(Math.random()*(supremeNum-1));
			selectionIndex = selectionIndex%supremeNum;
			Individual mama = this.generation.get(selectionIndex);
			this.generation.add(new Individual(papa,mama));
		}
		
		for(int i=supremeNum;i<maxPopulation;i+=numofThread) {
			ArrayList<Individual> thread = new ArrayList<Individual>();
			for(int j=0;j<numofThread;j++) {
				thread.add(this.generation.get(i+j));
			}
			thread.get(0).start();
			thread.get(1).start();
			thread.get(2).start();
			thread.get(3).start();
			thread.get(4).start();
			try {
				thread.get(0).join();
				thread.get(1).join();
				thread.get(2).join();
				thread.get(3).join();
				thread.get(4).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.generation.sort(null);
	}
//***********************************************************************************************************************//
}
