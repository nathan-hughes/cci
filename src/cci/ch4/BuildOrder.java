package cci.ch4;

import java.util.*;
import cci.test.*;
import static cci.test.Asserts.*;

public class BuildOrder {

    public static void main(String... args) {
        TestRunner.runAllTests(BuildOrder.class);
    }

    List<String> buildOrder(List<String[]> deps) {
        Map<String, GraphNode> graph = buildGraph(deps);
        List<String> build = new ArrayList<>();
        while (graph.size() > 0) {
            build.addAll(removeNodesWithNoIncomingEdges(graph));
            printGraph(graph);
            System.out.println("build order so far = " + build);
        }
        return build;
    }

    static Map<String, GraphNode> buildGraph(List<String[]> deps) {
        Map<String, GraphNode> graph = new HashMap<>();
        for (String[] dep : deps) {
            GraphNode n0 = createOrGetNode(dep[0], graph);
            GraphNode n1 = createOrGetNode(dep[1], graph);
            n1.incoming.add(n0);
            n0.outgoing.add(n1);
        }
        printGraph(graph);
        return graph; 
    } 

    static void printGraph(Map<String, GraphNode> graph) {
        for (Map.Entry<String, GraphNode> e : graph.entrySet()) {
            GraphNode node = e.getValue();
            System.out.println(node.name + ", incoming= " + node.incoming + ", outgoing= " + node.outgoing);
        } 
    }

    static GraphNode createOrGetNode(String name, Map<String, GraphNode> graph) {
        if (graph.containsKey(name)) return graph.get(name);
        GraphNode node = new GraphNode(name);
        graph.put(name, node);
        return node;
    }

    static List<String> removeNodesWithNoIncomingEdges(Map<String, GraphNode> graph) {
        List<String> nodes = new ArrayList<>();
        for (String s: graph.keySet()) {
            GraphNode n = graph.get(s);
            if (n.incoming.size() == 0) {
                System.out.println("found node " + s + " with no incoming edges");
                nodes.add(s);
                for (Map.Entry<String, GraphNode> e : graph.entrySet()) {
                    List<GraphNode> inc = e.getValue().incoming;
                    if (inc.contains(n)) {
                        System.out.println("removing " + n + " from incoming of " + e.getKey());
                        inc.remove(n);
                    }
                }
            }
        }
        for (String s : nodes) {
            System.out.println("removing " + s + " from graph");
            graph.remove(s);
        }
        return nodes;
    }

    public static class GraphNode {

        String name;
        List<GraphNode> incoming = new ArrayList<>();
        List<GraphNode> outgoing = new ArrayList<>();

        public GraphNode(String name) {this.name = name;}

        public boolean equals(Object other) {
            if (other == null) return false;
            if (!(other instanceof GraphNode)) return false;
            return ((GraphNode)other).name.equals(name);
        }

        public int hashCode() { return name.hashCode(); }

        public String toString() {
            return "<" + name + ">";
        }
    }

    List<String[]> deps = initializeTestData();

    static List<String[]> initializeTestData() {
        List<String[]> arrays = new ArrayList<>();
        arrays.add(new String[] {"A", "D"});
        arrays.add(new String[] {"F", "B"});
        arrays.add(new String[] {"B", "D"});
        arrays.add(new String[] {"F", "A"});
        arrays.add(new String[] {"D", "C"});
        return arrays;
    }

    @Test public void test() {
        assertEq(buildOrder(deps).toString(), "[F, A, B, D, C]");
    }
}
