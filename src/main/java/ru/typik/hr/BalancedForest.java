package ru.typik.hr;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BalancedForest {
	
	public static int balancedForest(int[] values,int[][] edges) {
		Integer minAddValue = null;
		for( int i = 0; i < edges.length - 1; ++i ) {
			for( int j = i + 1; j < edges.length; ++j ) {
				System.out.println( String.format( "Cut %s->%s and %s->%s" , 
						edges[i][0] , edges[i][1] , edges[j][0] , edges[j][1] ) );
				
				int[] graphIndexes = new int[values.length];
				boolean[] markEdges = new boolean[edges.length];
				markEdges[i] = true;
				markEdges[j] = true;
				walkThroughEdges( edges, graphIndexes , markEdges , 1 );
				walkThroughEdges( edges, graphIndexes , markEdges , 2 );
				walkThroughEdges( edges, graphIndexes , markEdges , 3 );
				
				System.out.println( String.format( "Graph indexes : %s" , 
						IntStream.of( graphIndexes ).boxed().collect( Collectors.toList() ) ) );
				
				long sum1 = getSum(values, graphIndexes, 1);
				long sum2 = getSum(values, graphIndexes, 2);
				long sum3 = getSum(values, graphIndexes, 3);
				
				Integer addValue = getValueToAdd(sum1, sum2, sum3);
				minAddValue = minAddValue == null ? addValue:
					addValue != null && addValue < minAddValue ? addValue:
						minAddValue;
			}
		}
		return minAddValue == null ? -1 : minAddValue;
	}

	private static void walkThroughEdges( int[][] edges, int[] graphIndexes, boolean[] markEdges, int graphNum) {
		int startIndex = -1;
		for( int i = 0; i < markEdges.length; ++i ) {
			if ( !markEdges[i] ) {
				startIndex = i;
				markEdges[i] = true;
				break;
			}
		}
		if ( startIndex == -1 ) {
			return;
		}
		graphIndexes[edges[startIndex][0]-1] = graphNum;
		graphIndexes[edges[startIndex][1]-1] = graphNum;
		
		boolean isAnyChanged = true;
		while(isAnyChanged) {
			isAnyChanged = false;
			for( int i = startIndex + 1; i < markEdges.length; ++i ) {
				if ( !markEdges[i] ) {
					int[] edge = edges[i];
					if ( graphIndexes[edge[0]-1] == graphNum || graphIndexes[edge[1]-1] == graphNum ) {
						graphIndexes[edge[0]-1] = graphNum;
						graphIndexes[edge[1]-1] = graphNum;
						markEdges[i] = true;
						isAnyChanged = true;
					}
				}
			}
		}
	}
	private static Integer getValueToAdd(long sum1, long sum2, long sum3) {
		if (sum1 == sum2 && sum3 < sum1) {
			return (int) (sum1 - sum3);
		} else if (sum2 == sum3 && sum1 < sum2) {
			return (int) (sum2 - sum1);
		} else if (sum1 == sum3 && sum2 < sum1) {
			return (int) (sum1 - sum2);
		} else if (sum1 + sum2 == sum3) {
			return (int) sum3;
		} else if (sum2 + sum3 == sum1) {
			return (int) sum1;
		} else if (sum1 + sum3 == sum2) {
			return (int) sum2;
		} else {
			return null;
		}
	}

	private static long getSum(int[] values, int[] graphIndex, int graphNum) {
		long sum = 0;
		for (int i = 0; i < graphIndex.length; ++i) {
			if (graphIndex[i] == graphNum) {
				sum += values[i];
			}
		}
		return sum;

	}
}
