package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreedyFloristTest {
	
	@Test
	public void test() {
		assertEquals( 29 , GreedyFlorist.getMinimumCost( 3 , new int[] { 1 , 3 , 5 , 7 , 9 } ) );
	}

}
