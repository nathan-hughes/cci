package cci.ch3;

import java.util.*;
import cci.test.*;

public class RouteBetweenNodes {

    public static boolean bfSearch(Graph g, String s) {
        for (GraphNode n : g.nodes) {
            if (bfSearch(n, s)) { return true;}
        }
        return false;
    }

    public static boolean bfSearch(GraphNode graphNode, String searchTargetName) {
        Queue<GraphNode> queue = new LinkedList<>();
        graphNode.visited = true;
        queue.add(graphNode);
        while (!queue.isEmpty()) {
            GraphNode n = queue.remove();
            if (visit(n, searchTargetName)) { 
                return true;
            }
            for (GraphNode c : n.children) {
                if (!c.visited) {
                    c.visited = true;
                    queue.add(c);
                }
            }
        }
        return false;
    } 

    public static boolean visit(GraphNode n, String searchTargetName) {
        return n.name.equals(searchTargetName);
    }

    public static void main(String ... args) {
        TestRunner.runAllTests(RouteBetweenNodes.class);
    }

    @Test
    public void oneNodeGraphNodeFound() {
        GraphNode aNode = GraphNode.build("a");      
        Graph g = new Graph();
        g.nodes = new GraphNode[]{ aNode };
        Asserts.assertTrue(bfSearch(g, "a"));
    }

    @Test
    public void oneNodeGraphNodeNotFound() {
        GraphNode aNode = GraphNode.build("a");      
        Graph g = new Graph();
        g.nodes = new GraphNode[]{ aNode };
        Asserts.assertFalse(bfSearch(g, "b"));
    }

    @Test
    public void twoNodeConnectedGraph() {
        GraphNode bNode = GraphNode.build("b");
        GraphNode aNode = GraphNode.build("a", bNode);      
        Graph g = new Graph();
        g.nodes = new GraphNode[]{ aNode };
        Asserts.assertTrue(bfSearch(g, "b"));
    }

    @Test 
    public void threeNodeConnectedGraph() {
        GraphNode cNode = GraphNode.build("c");
        GraphNode bNode = GraphNode.build("b", cNode);
        GraphNode aNode = GraphNode.build("a", bNode);
        Graph g = new Graph();
        g.nodes = new GraphNode[]{ aNode };
        Asserts.assertTrue(bfSearch(g, "c"));        
    }

    @Test
    public void threeNodeConnectedGraphNodeNotFound() {
        GraphNode cNode = GraphNode.build("c");
        GraphNode bNode = GraphNode.build("b", cNode);
        GraphNode aNode = GraphNode.build("a", bNode);
        Graph g = new Graph();
        g.nodes = new GraphNode[]{ aNode };
        Asserts.assertFalse(bfSearch(g, "d"));        
    }
}

final class Graph  {
    public GraphNode[] nodes;
}

final class GraphNode {

    public String name = "";
    public GraphNode[] children;
    public boolean visited;

    public String toString() { return "[" + name + "]";}

    static GraphNode build(String name, GraphNode ... children) {
        GraphNode n = new GraphNode();
        n.name = name;
        n.children = new GraphNode[children.length];
        for (int i = 0; i < children.length; i++) {
            n.children[i] = children[i];
        }
        return n;
    }
}