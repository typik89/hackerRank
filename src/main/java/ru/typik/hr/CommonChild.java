package ru.typik.hr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonChild {
	
	static String LCS( String s1 , int i1 , String s2 , int i2 , String[][] cache ) {
		if ( i1 < 0 || i2 < 0 ) {
			return "";
		}
		if ( cache[i1][i2] != null) {
			return cache[i1][i2];
		}
		String result = "";
		if ( s1.charAt( i1 ) == s2.charAt( i2 ) ) {
			result = LCS( s1 , i1 - 1 , s2 , i2 - 1 , cache ) + s1.charAt( i1 );
		}else {
			String word1 = LCS( s1 , i1 - 1, s2 , i2     , cache );
			String word2 = LCS( s1 , i1    , s2 , i2 - 1 , cache );
			result = word1.length() > word2.length() ? word1 : word2;
		}
		cache[i1][i2] = result;
		return result;
	}
	
	public static int commonChild( String s1 , String s2 ) {
		return LCS( s1 , s1.length() - 1 , s2 , s2.length() - 1 , new String[s1.length()][s2.length()] ).length();
	}
	
	
	
    public static int commonChild1(String s1, String s2) {
    	String[][] bestWords = new String[s1.length()][s2.length()];
    	
    	HashMap<Character,List<Integer>> indexedString = new HashMap<>(); 
    	int index = 0;
    	for( Character ch : s2.toCharArray() ) {
    		if ( indexedString.get( ch ) == null ) {
    			indexedString.put( ch , new ArrayList<>() );
    		}
    		indexedString.get( ch ).add( index++ );
    	}
    	
    	String world = findBestWord( s1 , 0 , indexedString , 0 , bestWords );
    	return world.length();
    }

	private static String findBestWord( String s1 , int s1index, HashMap<Character,List<Integer>>  s2 , int s2index , String[][] bestWords ) {
		if ( s1index >= bestWords.length || s2index >= bestWords[0].length ) {
			return "";
		}
		String bestWord = bestWords[s1index][s2index];
		if ( bestWord != null ) {
			return bestWord;
		}else {
			bestWord = "";
		}
		for( int i = s1index; i < s1.length(); i++ ) {
			Integer firstIndex = findFirst( s1.charAt( i ) , s2 , s2index );
			if ( firstIndex != null ) {
				String world = s1.charAt( i ) + findBestWord( s1 , i + 1 , s2 , firstIndex + 1 , bestWords );
				if ( bestWord.length() < world.length() ) {
					bestWord = world;
					bestWords[s1index][s2index] = bestWord;
				}
			}
			
		}
		return bestWord;
	}

	private static Integer findFirst(char ch,Map<Character, List<Integer>> map, int index) {
		if ( map.get( ch ) == null ) {
			return null;
		}
		for( int i : map.get( ch ) ) {
			if ( i >= index ) {
				return i;
			}
		}
		return null;
	}
}
