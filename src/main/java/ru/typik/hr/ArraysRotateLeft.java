package ru.typik.hr;


public class ArraysRotateLeft {

	public static int[] rotLeft(int[] arr, int index) {
		int[] newArr = new int[arr.length];
		int delta = arr.length - index;
		for( int i = 0; i < index; ++i ) {
			newArr[i + delta] = arr[i];
		}
		for ( int i = index; i < arr.length; ++i ) {
			newArr[i-index] = arr[i];
		}
		return newArr;
	}

}
