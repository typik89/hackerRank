package ru.typik.hr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ShortestReachGraph {
	
	public static class Node{
		private int index;
		private List<Node> connections = new ArrayList<>();
		
		public Node( int index ) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
		public List<Node> getConnections() {
			return connections;
		}
		public void addConnection(Node connection) {
			this.connections.add( connection );
		}
	}

	public static Integer[] getPaths( int countNodes, List<int[]> connections, int startIndex) {
		Node[] nodes = new Node[countNodes];
		IntStream.range( 0 , countNodes ).forEach( i -> nodes[i] = new Node( i ) );
		for( int[] connection : connections ) {
			nodes[connection[0] - 1 ].addConnection( nodes[connection[1] - 1 ] );
			nodes[connection[1] - 1 ].addConnection( nodes[connection[0] - 1 ] );
		}		
		Integer[] paths = new Integer[ countNodes - 1 ];
		IntStream.range( 0 , countNodes - 1 ).forEach( i -> paths[i] = -1 );
		go( nodes[startIndex-1] , null , nodes[startIndex-1] , paths , 0 );
		return paths;
	}

	private static void go( Node node , Node prevNode , Node startNode , Integer[] paths , int step ) {
		if ( node != startNode ) {
			int weight = step * 6;			
			int pathsIndex = node.getIndex() > startNode.getIndex() ? node.getIndex() - 1 :
				node.getIndex();
			if ( paths[ pathsIndex ] == -1 || paths[ pathsIndex ] > weight ) {
				paths[ pathsIndex ] = weight;
			}
		}
		for( Node n : node.getConnections() ) {
			if ( n != prevNode ) {
				go( n , node , startNode , paths , step + 1 );
			}
		}
	}
	
	
	public static String executeFromScanner( Scanner scanner ) {
		   int countTests = Integer.parseInt( scanner.nextLine().trim() );
       		StringBuilder sb = new StringBuilder();
	        for( int i = 0; i < countTests; ++i ) {
	        	String[] firstLine = scanner.nextLine().split( " " );
	        	
	        	int countNodes = Integer.parseInt( firstLine[0] );
	        	int countEdges = Integer.parseInt( firstLine[1] );
	        	
	        	List<int[]> connections = new ArrayList<>();
	        	for( int j = 0; j < countEdges; ++j ) {
	        		String[] connectionLine = scanner.nextLine().split( " " );
	        		int[] connection = new int[2];
	        		connection[0] = Integer.parseInt( connectionLine[0].trim() );
	        		connection[1] = Integer.parseInt( connectionLine[1].trim() );
	        		connections.add( connection );
	        	}
	        	int startIndex = Integer.parseInt( scanner.nextLine().trim() );
	        	
	        	Integer[] result = getPaths( countNodes , connections , startIndex );
	        	for( int j = 0; j < result.length; ++j ) {
	        		sb.append( result[j] );
	        		if ( j != result.length - 1 ) {
	        			sb.append( " " );
	        		}
	        	}
	        	if ( i != countTests - 1 ) {
	        		sb.append( "\n" );
	        	}
	        }
	        scanner.close();
	        return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println( executeFromScanner( new Scanner(System.in) ) );
    }

}
