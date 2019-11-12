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
	
	public static int getTreeSum( TreeNode root ) {
		int sum = root.getValue();
		for( TreeNode child : root.getChilds() ) {
			sum += getTreeSum( child );
		}
		return sum;
	}

	public static int getNodeValueToCreateBalancedTree(int[] values, int[][] edges) {
		for( int i = 0; i < edges.length - 1; ++i ) {
			for( int j = i + 1; j < edges.length; ++j ) {
				TreeNode[] forest = createTrees(values, edges, i, j);
				System.out.println( String.format( "%d,%d" , i,j) );
				for( TreeNode treeNode : forest ) {
					System.out.println( "sum:" + getTreeSum(treeNode) );
				}
			}
		}
		
		return -1;
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
