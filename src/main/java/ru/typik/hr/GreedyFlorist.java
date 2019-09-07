package ru.typik.hr;

import java.util.Arrays;

public class GreedyFlorist {
	
	static int getMinimumCost(int k, int[] c) {
		Arrays.sort( c );		
		int sum = 0;
		int iteration = 0;
		for( int i = c.length - 1; i >= 0 ; --i ) {
			sum += (iteration/k+1)*c[i];
			iteration++;
		}
		return sum;
    }

}
