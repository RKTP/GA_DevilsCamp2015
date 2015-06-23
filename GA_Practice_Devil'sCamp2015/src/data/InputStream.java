package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputStream {
	private static InputStream instance = new InputStream();
	private final int attNum = 294;
	private final int labelNum = 5;

//	private final int patternNum = 100;
//	private final int attNum = 10000;
//	private final int labelNum = 1;
	
	private InputStream() {
		//empty constructor
	}
	
	public Division setData(File file) throws FileNotFoundException {
		Division dataSet = new Division();
		
		String temp;
		Scanner scan = new Scanner(file);
		StringTokenizer token;
		int numofData=0;
		
		ArrayList<Double[]> att = new ArrayList<Double[]>();
		ArrayList<Boolean[]> label = new ArrayList<Boolean[]>();

//If you want to try train/test.dat, uncomment this source

		while(scan.hasNextLine()) {
			temp = scan.nextLine();
			token = new StringTokenizer(temp,",");
			
			Double attribute[] = new Double[attNum];
			Boolean labelArr[] = new Boolean[labelNum];
			
			for(int i=0;i<attNum;i++) {
				attribute[i] = Double.parseDouble(token.nextToken());
			}
			for(int i=0;i<labelNum;i++) {
				if(Integer.parseInt(token.nextToken())==1) {
					labelArr[i] = true;
				} else {
					labelArr[i] = false;
				}
			}
			att.add(attribute);
			label.add(labelArr);
			numofData++;
		}
		scan.close();

/*
		for(int k=0;k<patternNum;k++) {
			temp = scan.nextLine();
			token = new StringTokenizer(temp," ");
			
			Double attribute[] = new Double[attNum];
			
			for(int i=0;i<attNum;i++) {
				attribute[i] = Double.parseDouble(token.nextToken());
			}

			att.add(attribute);
		}
		for(int k=0;k<patternNum;k++) {
			temp = scan.nextLine();
			token = new StringTokenizer(temp," ");
			
			Boolean labelArr[] = new Boolean[labelNum];
			
			for(int i=0;i<labelNum;i++) {
				if(Integer.parseInt(token.nextToken())==1) {
					labelArr[i] = true;
				} else {
					labelArr[i] = false;
				}
			}
			label.add(labelArr);
		}
		scan.close();
*/
		
		dataSet.setAtt(att);
		dataSet.setLabel(label);
		dataSet.setNum(numofData);
		
		return dataSet;
	}
	
	public static InputStream getInstance() {
		return instance;
	}
}
