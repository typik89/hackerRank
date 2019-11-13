package ru.typik.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BalancedForest {
	
	public static class TreeNode{
		private int value;
		private TreeNode parent;
		private List<TreeNode> childs = new ArrayList<>();
		public TreeNode(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		public void addChild(TreeNode node) {
			childs.add( node );
		}
		public List<TreeNode> getChilds(){
			return childs;
		}
		public TreeNode getParent() {
			return parent;
		}
		public void setParent(TreeNode parent) {
			this.parent = parent;
		}
	}
	
	

	public static int balancedForest(int[] values, int[][] edges) {
		int addValue = -1;
		for( int i = 0; i < edges.length - 1; ++i ) {
			for( int j = i + 1; j < edges.length; ++j ) {
				TreeNode[] forest = createTrees(values, edges, i, j);
				long sum1 = getTreeSum( forest[0] );
				long sum2 = getTreeSum( forest[1] );
				long sum3 = getTreeSum( forest[2] );
				if ( sum1 == sum2 && sum3 < sum1 ) {
					addValue = getAddValue(addValue, (int)(sum2 - sum3) ); 
				}
				if ( sum2 == sum3 && sum1 < sum3 ) {
					addValue = getAddValue(addValue, (int)(sum3 - sum1) );
				}
				if ( sum1 == sum3 && sum2 < sum1 ) {
					addValue = getAddValue(addValue, (int)(sum1 - sum2) );
				}
			}
		}
		return addValue;
	}
	
	private static long getTreeSum( TreeNode root ) {
		int sum = root.getValue();
		for( TreeNode child : root.getChilds() ) {
			sum += getTreeSum( child );
		}
		return sum;
	}
	
	private static int getAddValue( int currentValue , int delta ) {
		if ( delta <= 0 ) {
			return currentValue;
		}else if ( currentValue < 0 ) {
			return delta;
		}else {
			return delta < currentValue ? delta : currentValue;
		}
	}

	private static TreeNode[] createTrees(int[] values,int[][] edges,int cut1, int cut2) {
		TreeNode[] nodes = new TreeNode[ values.length ];
		for( int i = 0; i < values.length; ++i ) {
			nodes[i] = new TreeNode( values[i] );
		}
		int i = 0;
		for( int[] edge : edges ) {
			if ( i != cut1 && i != cut2 ) {
				nodes[ edge[0] - 1 ].addChild( nodes[ edge[1] - 1 ] );
				nodes[ edge[1] -1 ].setParent( nodes[ edge[0] ] );
			}
			i++;
		}
		return Stream.of( nodes ).filter( el -> el.getParent() == null ).toArray( count -> new TreeNode[count] );
	}

}
