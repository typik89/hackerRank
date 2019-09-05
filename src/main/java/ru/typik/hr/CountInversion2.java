package ru.typik.hr;

public class CountInversion2 {
	
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
    	return arr.length == 0 ? 0l : sort( arr , 0 , arr.length - 1 , arr.clone() );
    }
    

	private static void swap(int[] arr, int i, int j) {
		int v = arr[i];
		arr[i] = arr[j];
		arr[j] = v;
	}

	private static long sort(int[] arr, int i, int j,int[] cloneArr) {
		int delta = j - i;
		if ( delta == 0 ) {
			return 0l;
		}else if ( delta == 1 ) {
			if ( arr[i] > arr[j] ) {
				swap( arr , i , j );
				return 1l;
			}else {
				return 0l;
			}
		}else{
			int middle = delta / 2 + i;
			long result = sort( arr , i , middle , cloneArr) + sort( arr , middle + 1 , j , cloneArr);
			result += merge( arr , i , middle , middle + 1 , j , cloneArr );
			return result;
		}
		
	}
	
	private static long merge(int[] arr, int s1, int e1, int s2, int e2 , int[] cloneArr ) {
		if ( arr[e1] <= arr[s2] ) {
			return 0l;
		}
		int i1 = s1;
		int i2 = s2;
		long result = 0l;
		
		int i = s1;
		while( i1 <= e1 || i2 <= e2 ) {
			if ( i2 > e2 || ( i1 <= e1 && arr[i1] <= arr[i2] ) ) {
				cloneArr[i] = arr[i1];
				result += i > i1 ? i - i1 : 0;
				i1++;
			}else {
				cloneArr[i] = arr[i2];
				i2++;
			}
			++i;
		}
		System.arraycopy( cloneArr, s1 , arr , s1 , e2 - s1 + 1 );
		return result;
	}

	static long mergeFast(int[] arr, int s1, int e1, int s2, int e2 , int[] cloneArr ) {
		if ( arr[e1] <= arr[s2] ) {
			return 0l;
		}
		int i1 = s1;
		int i2 = s2;
		long result = 0l;
		
		int i = s1;
		while( i1 <= e1 && i2 <= e2 ) {
			if ( arr[i1] <= arr[i2] ) {
				cloneArr[i] = arr[i1];
				result += i > i1 ? i - i1 : 0;
				i1++;
			}else {
				cloneArr[i] = arr[i2];
				i2++;
			}
			++i;
		}
		if ( i1 <= e1 ) {
			result += ( e1 - i1 + 1 ) * ( e2 - s2 + 1 );
			System.arraycopy( arr , i1 , cloneArr , i , e1 - i1 + 1 );
		}else if ( i2 <= e2 ) {
			System.arraycopy( arr , i2 , cloneArr , i , e2 - i2 + 1 );
		}
		System.arraycopy( cloneArr, s1 , arr , s1 , e2 - s1 + 1 );
		return result;
	}

}
