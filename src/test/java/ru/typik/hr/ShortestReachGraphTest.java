package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ShortestReachGraphTest {
	
	@Test
	public void test() throws IOException {		
		assertArrayEquals( 
				new Integer[] { 6 , 6 , -1 },
				ShortestReachGraph.getPaths( 
					4,
					Arrays.asList( 
							new int[] { 1 , 2 },
							new int[] { 1 , 3 } ),
					1 ) );
		assertArrayEquals( 
				new Integer[] { -1 , 6 },
				ShortestReachGraph.getPaths( 
					3,
					Arrays.asList( 
							new int[] { 2 , 3 } ),
					2 ) );
		
		
		assertEquals( 
				Files.readAllLines( Paths.get( "src/test/resources/shortestReachGraph/output01.txt" ) ).get( 0 ) , 
				ShortestReachGraph.executeFromScanner(
						new Scanner( Paths.get( "src/test/resources/shortestReachGraph/input01.txt" ) ) ) );
		
		
	}

}
