package ru.typik.hr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

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
        for( int i = 0; i < colors.length; ++i ) {
            nodes[i] = new Node( i , colors[i] );
        }
        for( int i = 0; i < graphFrom.length ; ++i ) {
            nodes[ graphFrom[i] - 1 ].addNode( nodes[ graphTo  [i] - 1 ] );
            nodes[ graphTo  [i] - 1 ].addNode( nodes[ graphFrom[i] - 1 ] );
        }
        
        Integer result = null;        
        for( Node node : nodes ) {
            if ( node.getColor() == color ) {
                Integer goResult = go( node , new HashSet<>() , color );
                result = result == null || ( goResult != null && goResult < result ) ? 
                        goResult :
                            result;
            }
        }
        return result;
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

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
