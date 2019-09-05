package ru.typik.hr;

public class CountInversion1 {
	private static int findIndexToInsert( int[] sortedArr , int startIndex , int endIndex , int value ) {
		if ( value <= sortedArr[startIndex] ) {
			return startIndex - 1;
		}
		if ( value > sortedArr[endIndex] ) {
			return endIndex;
		}
		if ( value == sortedArr[endIndex] ) {
			int i = endIndex;
			while( sortedArr[i] == value ) {
				--i;
			}
			return i;
		}
		int delta = endIndex - startIndex;
		if ( delta == 1 ) {
			return value <= sortedArr[startIndex] ? 
					startIndex-1 :
						( value <= sortedArr[endIndex] ? endIndex-1 : endIndex );
		}
		
		int middle = delta / 2 + delta % 2 + startIndex;
		return value <= sortedArr[ middle ] ? 
				findIndexToInsert(sortedArr, startIndex, middle, value) :
				findIndexToInsert(sortedArr, middle, endIndex, value);
	}
	static long swapCopyArr(int[] arr,int startIndex,int endIndex) {	
		int delta = endIndex - startIndex;
		if ( delta == 0 ) {
			return 0;
		}
		int v = arr[startIndex];
		System.arraycopy( arr , startIndex + 1 , arr , startIndex , delta );
		arr[endIndex] = v;
		return delta;
	}
	
    public static long countInversions(int[] arr) {
    	long inversionCount = 0;
    	for( int startIndex = arr.length - 2; startIndex >= 0; startIndex-- ) {
    		int index = findIndexToInsert(arr,startIndex+1,arr.length-1,arr[startIndex]);
    		inversionCount += swapCopyArr( arr , startIndex , index );
    	}
    	return inversionCount;
    }
}
