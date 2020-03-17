package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ArraysRotateLeftTest {
	
	@Test
	public void test() {
		assertArrayEquals( new int[] { 5 , 1, 2, 3, 4 }, ArraysRotateLeft.rotLeft( new int[] {1, 2, 3, 4, 5} , 4 ));
	}

}
