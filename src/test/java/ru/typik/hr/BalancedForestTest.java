package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BalancedForestTest {
	
	@Test
	public void test1() {
		assertEquals( 19 , 
				BalancedForest.getNodeValueToCreateBalancedTree(
						new int[] { 15, 12, 8, 14, 13 },
						new int[][] { 
							new int[] { 1,2 },
							new int[] { 1,3 },
							new int[] { 1,4 },
							new int[] { 4,5 }
						}) );
				
	}


}
