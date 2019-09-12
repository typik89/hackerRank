package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class FindTheNearestCloneTest {
	
	public static void runTestFromFile( String path ) throws IOException {
		List<String> lines = Files.readAllLines( Paths.get( path ) );
		
		List<Integer> arr1 = new ArrayList<>();
		List<Integer> arr2 = new ArrayList<>();
		for( int i = 1; i < lines.size() - 2; ++i ) {
			String line = lines.get( i );
			String[] lineArr = line.split( " " );
			arr1.add( Integer.parseInt( lineArr[0] ) );
			arr2.add( Integer.parseInt( lineArr[1] ) );
		}
		
		long[] colors = Stream.of( lines.get( lines.size() - 2 ).split( " " ) ).
				mapToLong( el -> Long.parseLong( el ) ).toArray();
		
		assertEquals(
				-1 ,
				FindTheNearestClone.findShortest(
						colors.length,
						arr1.stream().mapToInt( el -> (int)el ).toArray() ,
						arr2.stream().mapToInt( el -> (int)el ).toArray(),
						colors,
						Integer.parseInt( lines.get( lines.size() - 1 ) ) ) );
	}
	
	
	
	@Test
	public void test() throws IOException {
		assertEquals( 
				3,
				FindTheNearestClone.findShortest( 
					5 , 
					new int[] { 1 , 1 , 2 , 3 } , 
					new int[] { 2 , 3 , 4 , 5 } , 
					new long[] { 1l , 2l , 3l , 3l , 2l } , 
					2 
				) 
			);
		assertEquals( 
				-1,
				FindTheNearestClone.findShortest( 
					4 , 
					new int[] { 1 , 1 , 4 } , 
					new int[] { 2 , 3 , 2 } , 
					new long[] { 1l , 2l , 3l , 4l } , 
					2 
				) 
			);
		
		runTestFromFile( "src/test/resources/findTheNearestClone/input09.txt" );
	}

}
