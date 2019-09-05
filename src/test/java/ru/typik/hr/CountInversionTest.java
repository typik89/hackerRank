package ru.typik.hr;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class CountInversionTest {
	
	 public List<int[]> readTestCases( String uri ) throws IOException{
    	List<String> lines = Files.readAllLines( Paths.get( uri ) );
    	List<int[]> result = new ArrayList<>();
    	for( int i = 2; i < lines.size(); i+=2 ) {
    		result.add( 
    				Stream.of( lines.get( i ).split( " " ) ).
    				mapToInt( el -> Integer.parseInt( el ) ).
    				toArray() );
    	}
    	return result;
    }
	
    @Test
    public void test1() throws IOException {   
    	assertEquals( 1 , CountInversion1.countInversions( new int[] { 1 , 2 , 5 , 4 , 5 , 5 } ) ); 	
    	assertEquals( 0 , CountInversion1.countInversions( new int[] {} ) );
    	assertEquals( 4 , CountInversion1.countInversions( new int[] { 2, 1, 3, 1, 2 } ) );
    	assertEquals( 0 , CountInversion1.countInversions( new int[] { 1, 1, 1, 2, 2 } ) );
    	assertEquals( 4 , CountInversion1.countInversions( new int[] { 2, 1, 3, 1, 2 } ) );
    	assertEquals( 1 , CountInversion1.countInversions( new int[] { 1, 5, 3, 7 } ) );
    	

    	long start = System.currentTimeMillis();
    	
    	List<int[]> testCasesFromInput06 = readTestCases( "src/test/resources/countInversion/countInversion_input06.txt" );
    	assertEquals( 5 , testCasesFromInput06.size() );
    	assertEquals( 0         , CountInversion1.countInversions( testCasesFromInput06.get( 0 ) ) ); 
    	assertEquals( 61685993  , CountInversion1.countInversions( testCasesFromInput06.get( 1 ) ) );
    	assertEquals( 167393979 , CountInversion1.countInversions( testCasesFromInput06.get( 2 ) ) );
    	assertEquals( 208480185 , CountInversion1.countInversions( testCasesFromInput06.get( 3 ) ) );
    	assertEquals( 106312795 , CountInversion1.countInversions( testCasesFromInput06.get( 4 ) ) );
    	

    	List<int[]> testCasesFromInput11 = readTestCases( "src/test/resources/countInversion/countInversion_input11.txt" );
    	assertEquals( 15 , testCasesFromInput11.size() );
    	assertEquals( 2506500183l , CountInversion1.countInversions( testCasesFromInput11.get( 0  ) ) ); 
    	assertEquals( 2499877139l , CountInversion1.countInversions( testCasesFromInput11.get( 1  ) ) );
    	assertEquals( 2501897497l , CountInversion1.countInversions( testCasesFromInput11.get( 2  ) ) );
    	assertEquals( 2495565894l , CountInversion1.countInversions( testCasesFromInput11.get( 3  ) ) );
    	assertEquals( 2501269496l , CountInversion1.countInversions( testCasesFromInput11.get( 4  ) ) );
    	assertEquals( 2505761636l , CountInversion1.countInversions( testCasesFromInput11.get( 5  ) ) );
    	assertEquals( 2492093510l , CountInversion1.countInversions( testCasesFromInput11.get( 6  ) ) );
    	assertEquals( 2490880650l , CountInversion1.countInversions( testCasesFromInput11.get( 7  ) ) );
    	assertEquals( 2514132274l , CountInversion1.countInversions( testCasesFromInput11.get( 8  ) ) );
    	assertEquals( 2498621246l , CountInversion1.countInversions( testCasesFromInput11.get( 9  ) ) );
    	assertEquals( 2501530572l , CountInversion1.countInversions( testCasesFromInput11.get( 10 ) ) );
    	assertEquals( 2495267838l , CountInversion1.countInversions( testCasesFromInput11.get( 11 ) ) );
    	assertEquals( 2501569000l , CountInversion1.countInversions( testCasesFromInput11.get( 12 ) ) );
    	assertEquals( 2497925066l , CountInversion1.countInversions( testCasesFromInput11.get( 13 ) ) );
    	assertEquals( 2504717468l , CountInversion1.countInversions( testCasesFromInput11.get( 14 ) ) );

    	List<int[]> testCasesFromInput12 = readTestCases( "src/test/resources/countInversion/countInversion_input12.txt" );
    	assertEquals( 15 , testCasesFromInput12.size() );
    	assertEquals( 2506500141l , CountInversion1.countInversions( testCasesFromInput12.get( 0  ) ) ); 
    	assertEquals( 2499877242l , CountInversion1.countInversions( testCasesFromInput12.get( 1  ) ) );
    	assertEquals( 2501897494l , CountInversion1.countInversions( testCasesFromInput12.get( 2  ) ) );
    	assertEquals( 2495566249l , CountInversion1.countInversions( testCasesFromInput12.get( 3  ) ) );
    	assertEquals( 2501269610l , CountInversion1.countInversions( testCasesFromInput12.get( 4  ) ) );
    	assertEquals( 2505761919l , CountInversion1.countInversions( testCasesFromInput12.get( 5  ) ) );
    	assertEquals( 2492093573l , CountInversion1.countInversions( testCasesFromInput12.get( 6  ) ) );
    	assertEquals( 2490880710l , CountInversion1.countInversions( testCasesFromInput12.get( 7  ) ) );
    	assertEquals( 2514132263l , CountInversion1.countInversions( testCasesFromInput12.get( 8  ) ) );
    	assertEquals( 2498621061l , CountInversion1.countInversions( testCasesFromInput12.get( 9  ) ) );
    	assertEquals( 2501530362l , CountInversion1.countInversions( testCasesFromInput12.get( 10 ) ) );
    	assertEquals( 2495268114l , CountInversion1.countInversions( testCasesFromInput12.get( 11 ) ) );
    	assertEquals( 2501569219l , CountInversion1.countInversions( testCasesFromInput12.get( 12 ) ) );
    	assertEquals( 2497924834l , CountInversion1.countInversions( testCasesFromInput12.get( 13 ) ) );
    	assertEquals( 2504717657l , CountInversion1.countInversions( testCasesFromInput12.get( 14 ) ) );
    	

    	List<int[]> testCasesFromInput13 = readTestCases( "src/test/resources/countInversion/countInversion_input13.txt" );
    	assertEquals( 15 , testCasesFromInput13.size() );
    	assertEquals( 2506500154l , CountInversion1.countInversions( testCasesFromInput13.get( 0  ) ) ); 
    	assertEquals( 2499877170l , CountInversion1.countInversions( testCasesFromInput13.get( 1  ) ) );
    	assertEquals( 2501897228l , CountInversion1.countInversions( testCasesFromInput13.get( 2  ) ) );
    	assertEquals( 2495565902l , CountInversion1.countInversions( testCasesFromInput13.get( 3  ) ) );
    	assertEquals( 2501269518l , CountInversion1.countInversions( testCasesFromInput13.get( 4  ) ) );
    	assertEquals( 2505761608l , CountInversion1.countInversions( testCasesFromInput13.get( 5  ) ) );
    	assertEquals( 2492093746l , CountInversion1.countInversions( testCasesFromInput13.get( 6  ) ) );
    	assertEquals( 2490880601l , CountInversion1.countInversions( testCasesFromInput13.get( 7  ) ) );
    	assertEquals( 2514132325l , CountInversion1.countInversions( testCasesFromInput13.get( 8  ) ) );
    	assertEquals( 2498621203l , CountInversion1.countInversions( testCasesFromInput13.get( 9  ) ) );
    	assertEquals( 2501530571l , CountInversion1.countInversions( testCasesFromInput13.get( 10 ) ) );
    	assertEquals( 2495267862l , CountInversion1.countInversions( testCasesFromInput13.get( 11 ) ) );
    	assertEquals( 2501569028l , CountInversion1.countInversions( testCasesFromInput13.get( 12 ) ) );
    	assertEquals( 2497925255l , CountInversion1.countInversions( testCasesFromInput13.get( 13 ) ) );
    	assertEquals( 2504717487l , CountInversion1.countInversions( testCasesFromInput13.get( 14 ) ) );
    	
    	System.out.println( System.currentTimeMillis() - start );
    }
    
    
    

	 
	@Test
	public void test2() throws Exception{
    	assertEquals( 1 , CountInversion2.countInversions( new int[] { 1 , 2 , 5 , 4 , 5 , 5 } ) ); 	
    	assertEquals( 0 , CountInversion2.countInversions( new int[] {} ) );
    	assertEquals( 4 , CountInversion2.countInversions( new int[] { 2, 1, 3, 1, 2 } ) );
    	assertEquals( 0 , CountInversion2.countInversions( new int[] { 1, 1, 1, 2, 2 } ) );
    	assertEquals( 4 , CountInversion2.countInversions( new int[] { 2, 1, 3, 1, 2 } ) );
    	assertEquals( 1 , CountInversion2.countInversions( new int[] { 1, 5, 3, 7 } ) );	
    	
    	long start = System.currentTimeMillis();
    	
    	List<int[]> testCasesFromInput06 = readTestCases( "src/test/resources/countInversion/countInversion_input06.txt" );
    	assertEquals( 5 , testCasesFromInput06.size() );
    	assertEquals( 0         , CountInversion2.countInversions( testCasesFromInput06.get( 0 ) ) ); 
    	assertEquals( 61685993  , CountInversion2.countInversions( testCasesFromInput06.get( 1 ) ) );
    	assertEquals( 167393979 , CountInversion2.countInversions( testCasesFromInput06.get( 2 ) ) );
    	assertEquals( 208480185 , CountInversion2.countInversions( testCasesFromInput06.get( 3 ) ) );
    	assertEquals( 106312795 , CountInversion2.countInversions( testCasesFromInput06.get( 4 ) ) );
    	

    	List<int[]> testCasesFromInput11 = readTestCases( "src/test/resources/countInversion/countInversion_input11.txt" );
    	assertEquals( 15 , testCasesFromInput11.size() );
    	assertEquals( 2506500183l , CountInversion2.countInversions( testCasesFromInput11.get( 0  ) ) ); 
    	assertEquals( 2499877139l , CountInversion2.countInversions( testCasesFromInput11.get( 1  ) ) );
    	assertEquals( 2501897497l , CountInversion2.countInversions( testCasesFromInput11.get( 2  ) ) );
    	assertEquals( 2495565894l , CountInversion2.countInversions( testCasesFromInput11.get( 3  ) ) );
    	assertEquals( 2501269496l , CountInversion2.countInversions( testCasesFromInput11.get( 4  ) ) );
    	assertEquals( 2505761636l , CountInversion2.countInversions( testCasesFromInput11.get( 5  ) ) );
    	assertEquals( 2492093510l , CountInversion2.countInversions( testCasesFromInput11.get( 6  ) ) );
    	assertEquals( 2490880650l , CountInversion2.countInversions( testCasesFromInput11.get( 7  ) ) );
    	assertEquals( 2514132274l , CountInversion2.countInversions( testCasesFromInput11.get( 8  ) ) );
    	assertEquals( 2498621246l , CountInversion2.countInversions( testCasesFromInput11.get( 9  ) ) );
    	assertEquals( 2501530572l , CountInversion2.countInversions( testCasesFromInput11.get( 10 ) ) );
    	assertEquals( 2495267838l , CountInversion2.countInversions( testCasesFromInput11.get( 11 ) ) );
    	assertEquals( 2501569000l , CountInversion2.countInversions( testCasesFromInput11.get( 12 ) ) );
    	assertEquals( 2497925066l , CountInversion2.countInversions( testCasesFromInput11.get( 13 ) ) );
    	assertEquals( 2504717468l , CountInversion2.countInversions( testCasesFromInput11.get( 14 ) ) );

    	List<int[]> testCasesFromInput12 = readTestCases( "src/test/resources/countInversion/countInversion_input12.txt" );
    	assertEquals( 15 , testCasesFromInput12.size() );
    	assertEquals( 2506500141l , CountInversion2.countInversions( testCasesFromInput12.get( 0  ) ) ); 
    	assertEquals( 2499877242l , CountInversion2.countInversions( testCasesFromInput12.get( 1  ) ) );
    	assertEquals( 2501897494l , CountInversion2.countInversions( testCasesFromInput12.get( 2  ) ) );
    	assertEquals( 2495566249l , CountInversion2.countInversions( testCasesFromInput12.get( 3  ) ) );
    	assertEquals( 2501269610l , CountInversion2.countInversions( testCasesFromInput12.get( 4  ) ) );
    	assertEquals( 2505761919l , CountInversion2.countInversions( testCasesFromInput12.get( 5  ) ) );
    	assertEquals( 2492093573l , CountInversion2.countInversions( testCasesFromInput12.get( 6  ) ) );
    	assertEquals( 2490880710l , CountInversion2.countInversions( testCasesFromInput12.get( 7  ) ) );
    	assertEquals( 2514132263l , CountInversion2.countInversions( testCasesFromInput12.get( 8  ) ) );
    	assertEquals( 2498621061l , CountInversion2.countInversions( testCasesFromInput12.get( 9  ) ) );
    	assertEquals( 2501530362l , CountInversion2.countInversions( testCasesFromInput12.get( 10 ) ) );
    	assertEquals( 2495268114l , CountInversion2.countInversions( testCasesFromInput12.get( 11 ) ) );
    	assertEquals( 2501569219l , CountInversion2.countInversions( testCasesFromInput12.get( 12 ) ) );
    	assertEquals( 2497924834l , CountInversion2.countInversions( testCasesFromInput12.get( 13 ) ) );
    	assertEquals( 2504717657l , CountInversion2.countInversions( testCasesFromInput12.get( 14 ) ) );
    	

    	List<int[]> testCasesFromInput13 = readTestCases( "src/test/resources/countInversion/countInversion_input13.txt" );
    	assertEquals( 15 , testCasesFromInput13.size() );
    	assertEquals( 2506500154l , CountInversion2.countInversions( testCasesFromInput13.get( 0  ) ) ); 
    	assertEquals( 2499877170l , CountInversion2.countInversions( testCasesFromInput13.get( 1  ) ) );
    	assertEquals( 2501897228l , CountInversion2.countInversions( testCasesFromInput13.get( 2  ) ) );
    	assertEquals( 2495565902l , CountInversion2.countInversions( testCasesFromInput13.get( 3  ) ) );
    	assertEquals( 2501269518l , CountInversion2.countInversions( testCasesFromInput13.get( 4  ) ) );
    	assertEquals( 2505761608l , CountInversion2.countInversions( testCasesFromInput13.get( 5  ) ) );
    	assertEquals( 2492093746l , CountInversion2.countInversions( testCasesFromInput13.get( 6  ) ) );
    	assertEquals( 2490880601l , CountInversion2.countInversions( testCasesFromInput13.get( 7  ) ) );
    	assertEquals( 2514132325l , CountInversion2.countInversions( testCasesFromInput13.get( 8  ) ) );
    	assertEquals( 2498621203l , CountInversion2.countInversions( testCasesFromInput13.get( 9  ) ) );
    	assertEquals( 2501530571l , CountInversion2.countInversions( testCasesFromInput13.get( 10 ) ) );
    	assertEquals( 2495267862l , CountInversion2.countInversions( testCasesFromInput13.get( 11 ) ) );
    	assertEquals( 2501569028l , CountInversion2.countInversions( testCasesFromInput13.get( 12 ) ) );
    	assertEquals( 2497925255l , CountInversion2.countInversions( testCasesFromInput13.get( 13 ) ) );
    	assertEquals( 2504717487l , CountInversion2.countInversions( testCasesFromInput13.get( 14 ) ) );
    	

    	List<int[]> testCasesFromInput7 = readTestCases( "src/test/resources/countInversion/countInversion_input07.txt" );
    	assertEquals( 5           , testCasesFromInput7.size() );
    	assertEquals( 0           , CountInversion2.countInversions( testCasesFromInput7.get( 0  ) ) ); 
    	assertEquals( 0           , CountInversion2.countInversions( testCasesFromInput7.get( 1  ) ) );
    	assertEquals( 0           , CountInversion2.countInversions( testCasesFromInput7.get( 2  ) ) );
    	assertEquals( 4999950000l , CountInversion2.countInversions( testCasesFromInput7.get( 3  ) ) );
    	assertEquals( 4999950000l , CountInversion2.countInversions( testCasesFromInput7.get( 4  ) ) );
    	
    	
    	System.out.println( System.currentTimeMillis() - start );
		
	}
}
