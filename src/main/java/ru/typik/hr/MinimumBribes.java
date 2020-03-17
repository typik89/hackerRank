package ru.typik.hr;

import java.util.Arrays;

public class MinimumBribes {

	public static int minimumBribes(int[] arr) {
		boolean isChanged = true;
		int swapCount = 0;
		int startIndex = arr.length - 2;
		while( isChanged ) {
			//System.out.println( Arrays.toString( arr ) );
			isChanged = false;
			
			for( int i = startIndex; i >= 0; --i ) {
				int val = arr[i] - 1;
				if ( val - i > 2 ) {
					return -1;
				}else if ( val - i == 1 ) {
					swapCount += 1;
					arr[i] = arr[i+1];
					arr[i+1] = val + 1;
					isChanged = true;
					startIndex = i + 1;
					break;
				}else if ( val - i == 2 ) {
					swapCount +=2;
					arr[i] = arr[i+1];
					arr[i+1] = arr[i + 2];
					arr[i+2] = val + 1;
					isChanged = true;
					startIndex = i + 2;
					break;
				}
			}
		}
		return swapCount;
	}

}
