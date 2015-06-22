package algorithm;
import java.util.*;
//prop1
public class Generation {
	private int genNum = 0;
	private final int pNum=50;
	private final int maxSupremeElem = 10;
	private Vector<Gene> genePopulation;
	private Vector<Gene> supremeSet;
	private int maxGen;
	private final int numofThread=5;
	
	public static DivideSet dataSet = new DivideSet();

//Start the algorithm by initializing first Generation//
	public Generation(int maxGen) {
		this.maxGen = maxGen;
		initGen();
	}
	
//Structure of the algorithm//
	public void algorithm() {
		while(genNum<this.maxGen) {
			nextGen();
			supremeGene();
			System.out.println("GENERATION " +genNum+ " GENERATED");
		}
		supremeSet.elementAt(0).saveOnlyAcc();
		supremeSet.elementAt(0).finalAccuracy(dataSet);
		supremeSet.elementAt(0).showAccuracy();
		System.out.println("PROCESS OVER");
	}

//Initializing//
	private void initGen() {
		genePopulation = new Vector<Gene>();
		for(int index=0;index<pNum;index+=numofThread) {
			Vector<Gene> temp = new Vector<Gene>();
			for(int sIndex=0;sIndex<numofThread;sIndex++) {
				temp.add(new Gene());
			}
			temp.elementAt(0).start();
			temp.elementAt(1).start();
			temp.elementAt(2).start();
			temp.elementAt(3).start();
			temp.elementAt(4).start();
//			temp.elementAt(5).start();
//			temp.elementAt(6).start();
//			temp.elementAt(7).start();
//			temp.elementAt(8).start();
//			temp.elementAt(9).start();
			
			try {
				temp.elementAt(0).join();
				temp.elementAt(1).join();
				temp.elementAt(2).join();
				temp.elementAt(3).join();
				temp.elementAt(4).join();
//				temp.elementAt(5).join();
//				temp.elementAt(6).join();
//				temp.elementAt(7).join();
//				temp.elementAt(8).join();
//				temp.elementAt(9).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.genePopulation.addAll(temp);
		}
		genNum++;
		supremeGene();
		System.out.println("GENERATION 1 INITIALIZED");
	}
	
	
	
	
//***********************************************************************************************************//
//***********************************************************************************************************//
//New Generation//
	private void nextGen() {
		for(int index=maxSupremeElem;index<pNum;index++) {
			genePopulation.remove(maxSupremeElem);
		}
		for(int index=maxSupremeElem;index<pNum;index+=numofThread) {
			Vector<Gene> temp = new Vector<Gene>();
			for(int sIndex=0;sIndex<numofThread;sIndex++) {
				int fGene = (int)(maxSupremeElem*Math.random());
				int sGene = ((int)((maxSupremeElem-1)*Math.random()) + fGene)%10;
				temp.add(new Gene(supremeSet.elementAt(fGene).getGene(),supremeSet.elementAt(sGene).getGene()));
			}
			temp.elementAt(0).start();
			temp.elementAt(1).start();
			temp.elementAt(2).start();
			temp.elementAt(3).start();
			temp.elementAt(4).start();
//			temp.elementAt(5).start();
//			temp.elementAt(6).start();
//			temp.elementAt(7).start();
//			temp.elementAt(8).start();
//			temp.elementAt(9).start();
			
			try {
				temp.elementAt(0).join();
				temp.elementAt(1).join();
				temp.elementAt(2).join();
				temp.elementAt(3).join();
				temp.elementAt(4).join();
//				temp.elementAt(5).join();
//				temp.elementAt(6).join();
//				temp.elementAt(7).join();
//				temp.elementAt(8).join();
//				temp.elementAt(9).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			genePopulation.addAll(temp);
		}
		genNum++;
	}
//***********************************************************************************************************//
//***********************************************************************************************************//

	
	
	
//Select supreme gene from current generation//
	private void supremeGene() {
		supremeSet = new Vector<Gene>();
		genePopulation.sort(null);
		for(int index=0;index<maxSupremeElem;index++) {
			supremeSet.add(genePopulation.elementAt(index));
			System.out.println((index+1) + " : " + supremeSet.elementAt(index).showGene());
		}
	}
}
