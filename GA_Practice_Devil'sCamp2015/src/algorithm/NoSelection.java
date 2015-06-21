package algorithm;

import java.util.ArrayList;

public class NoSelection extends Individual {
	public NoSelection() {
		super();
		ArrayList<Integer> all = new ArrayList<Integer>();
		for(int i=0;i<294;i++) {
			all.add(i);
		}
		super.gene = all;
	}
}
