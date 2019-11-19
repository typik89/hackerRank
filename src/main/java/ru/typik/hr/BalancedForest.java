package ru.typik.hr;

import java.util.ArrayList;
import java.util.List;

public class BalancedForest {
	
	public static class Node{
		private int id;
		private int value;
		private List<Node> connections = new ArrayList<>();
		public Node(int id,int value) {
			this.value = value;
			this.setId(id);
		}
		public int getValue() {
			return value;
		}
		public void removeConnection(Node node) {
			connections.remove( node );
		}
		public void addConnection(Node node) {
			connections.add( node );
		}
		public List<Node> getConnections() {
			return connections;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	}

	public static int balancedForest(int[] values, int[][] edges) {
		Node[] nodes = new Node[values.length];
		Integer minResult = null;
		for( int i = 0; i < values.length; ++i ) {
			nodes[i] = new Node(i, values[i]);
		}
		for( int[] edge : edges ) {
			add(nodes, edge);
		}
		for( int i = 0; i < edges.length - 1; ++i ) {
			cut(nodes, edges[i]);
			for( int j = 0; j < edges.length; ++j ) {
				cut(nodes, edges[j]);
				int[] graphIndex = new int[nodes.length];
				int graphNum = 0;
				for( Node node : nodes ) {
					if ( graphIndex[node.getId()] < 1 ) {
						graphNum++;
						walkToGraphAndMark(node,graphIndex,graphNum);
					}
					if ( graphNum == 3 ) {
						break;
					}
				}
				long sum1 = getSum(nodes,graphIndex,1);
				long sum2 = getSum(nodes,graphIndex,2);
				long sum3 = getSum(nodes,graphIndex,3);
				int newResult = getValueToAdd( sum1 , sum2 , sum3 );
				minResult = minResult == null || newResult < minResult ? 
						newResult : minResult;
				add(nodes,edges[j]);
			}
			add(nodes,edges[i]);
		}
		return minResult == null ? -1 : minResult;
	}
	
	private static int getValueToAdd(long sum1, long sum2, long sum3) {
		
	}

	private static long getSum(Node[] nodes, int[] graphIndex, int graphNum) {
		long sum = 0;
		for( int i = 0; i < graphIndex.length; ++i) {
			if ( graphIndex[i] == graphNum ) {
				sum += nodes[i].getValue();
			}
		}
		return sum;
		
	}

	private static void walkToGraphAndMark(Node node, int[] graphIndex, int graphNum) {
		graphIndex[node.getId()] = graphNum;
		for( Node connection : node.getConnections() ) {
			if ( graphIndex[node.getId()] < 1 ) {
				walkToGraphAndMark(connection, graphIndex, graphNum);
			}
		}
	}

	private static void cut(Node[] nodes,int[] edge) {
		nodes[edge[1]-1].removeConnection(nodes[edge[0]-1]);
		nodes[edge[0]-1].removeConnection(nodes[edge[1]-1]);
	}
	private static void add( Node[] nodes , int[] edge ) {
		nodes[edge[0]-1].addConnection( nodes[edge[1]-1] );
		nodes[edge[1]-1].addConnection( nodes[edge[0]-1] );		
	}
	
	private static long getSum(Node node,boolean[] passed) {
		long sum = node.getValue();
		passed[node.getId()] = true;
		for( Node connection : node.getConnections() ) {
			if ( !passed[connection.getId()] ) {
				sum += getSum(connection, passed);
			}
		}
		return sum;
	}
	
	
}
