package ru.typik.hr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindTheNearestClone {
	
	public static class Node{
		private int index;
		private long color;
		private Set<Node> nodes = new HashSet<>();

		public Node( int index , long color ) {
			this.index = index;
			this.color = color;
		}
		
		public int getIndex() {
			return index;
		}
		public long getColor() {
			return color;
		}
		
		public Set<Node> getNodes() {
			return nodes;
		}

		public void addNode(Node node) {
			nodes.add( node );
		}
		
	}
	
	public static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] colors, int color) {
		Node[] nodes = new Node[ colors.length ];
		List<Node> nodesWithColor = new ArrayList<>();
		for( int i = 0; i < colors.length; ++i ) {
			nodes[i] = new Node( i , colors[i] );
			if ( nodes[i].getColor() == color ) {
				nodesWithColor.add( nodes[i] );
			}
		}
		if ( nodesWithColor.size() < 2 ) {
			return -1;
		}
		
		for( int i = 0; i < graphFrom.length ; ++i ) {
			nodes[ graphFrom[i] - 1 ].addNode( nodes[ graphTo  [i] - 1 ] );
			nodes[ graphTo  [i] - 1 ].addNode( nodes[ graphFrom[i] - 1 ] );
		}
		
		Integer result = null;		
		for( Node node : nodesWithColor ) {
			Integer goResult = go( node , new HashSet<>() , color );
			result = result == null || ( goResult != null && goResult < result ) ? 
					goResult :
						result;
		}
		return result == null ? -1 : result;
    }

	private static Integer go( Node node , HashSet<Node> path , long color ) {
		if ( path.contains( node ) ) {
			return null;
		}
		if ( node.getColor() == color && !path.isEmpty() ) {
			return path.size();
		}
		path.add( node );
		Integer result = null;		
		for( Node connectedNode : node.getNodes() ) {
			Integer goResult = go( connectedNode , new HashSet<>( path ) , color );
			result = result == null || ( goResult != null && goResult < result ) ? 
					goResult :
						result;
		}
		return result;
	}

}
