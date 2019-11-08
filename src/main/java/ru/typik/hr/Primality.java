package ru.typik.hr;

public class Primality {

	public static boolean isPrimality(int num) {
		if ( num == 1 ) {
			return false;
		}
		for( int i = 2; i < num /2 + 1; ++i ) {
			if ( num % i == 0 ) {
				return false;
			}
		}
		return true;
	}

}
