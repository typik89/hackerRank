package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumBribesTest {
	
	@Test
	public void test() {
		//assertEquals( 3 , MinimumBribes.minimumBribes( new int[] {2, 1, 5, 3, 4} ) );
		//assertEquals( -1 , MinimumBribes.minimumBribes( new int[] {2, 5, 1, 3, 4} ) );
		assertEquals( 7 , MinimumBribes.minimumBribes( new int[] {1, 2, 5, 3, 7, 8, 6, 4} ) );
	}
	

}
