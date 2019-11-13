package ru.typik.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BalancedForest {
	
	public static class TreeNode{
		private int id;
		private int value;
		private TreeNode parent;
		private List<TreeNode> childs = new ArrayList<>();
		public TreeNode(int index,int value) {
			this.value = value;
			this.id = index;
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
		public int getId() {
			return id;
		}
		
		public TreeNode clone() {
			TreeNode treeNode = new TreeNode( getId() , getValue() );
			for( TreeNode child : treeNode.getChilds() ) {
				TreeNode newChild = child.clone();
				newChild.setParent( treeNode );
				treeNode.addChild( newChild );
			}
			return treeNode;
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
				System.out.println( String.format( "%s %s %s", sum1, sum2, sum3 ) ); 
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
	
	
	private static TreeNode[] cutTrees( TreeNode node , int[] cutEdge1, int[] cutEdge2 ) {
		TreeNode treeNodeForCut = node.clone();
		TreeNode[] cutNodes = new TreeNode[4];	
		return null;
	}
	
	

	private static TreeNode[] createTrees(int[] values,int[][] edges,int cut1, int cut2) {
		TreeNode[] nodes = new TreeNode[ values.length ];
		for( int i = 0; i < values.length; ++i ) {
			nodes[i] = new TreeNode( i , values[i] );
		}
		int i = 0;
		for( int[] edge : edges ) {
			if ( i != cut1 && i != cut2 ) {
				TreeNode node1 = nodes[ edge[0] - 1 ];
				TreeNode node2 = nodes[ edge[1] - 1 ];
				if ( node2.getParent() != null ) {
					node2.addChild( node1 );
				}else {
					node1.addChild( node2 );
				}
			}
			i++;
		}
		return Stream.of( nodes ).filter( el -> el.getParent() == null ).toArray( count -> new TreeNode[count] );
	}

}
