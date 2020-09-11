package cci.ch4;

import static cci.test.Asserts.*;
import java.util.*;
import cci.test.*;

public class ListOfDepths {

    public static void main(String ... args) {
        TestRunner.runAllTests(ListOfDepths.class);        
    }

    public static List<List<BinaryTreeNode>> listOfDepths(BinaryTreeNode root) {
        List<List<BinaryTreeNode>> listOfLists = new ArrayList<>();
        breadthFirstSearch(root, listOfLists); 
        return listOfLists;
    }

    public static void breadthFirstSearch(BinaryTreeNode node, List<List<BinaryTreeNode>> listOfLists) { 
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(0, node));
        while (!queue.isEmpty()) {
            NodeInfo nodeInfo = queue.remove();
            visit(nodeInfo, listOfLists);
            BinaryTreeNode n = nodeInfo.node;
            if (n.left != null) {
                queue.add(new NodeInfo(nodeInfo.depth + 1, n.left));
            }
            if (n.right != null) {
                queue.add(new NodeInfo(nodeInfo.depth + 1, n.right));
            }
        }
    }

    static void visit(NodeInfo nodeInfo, List<List<BinaryTreeNode>> listOfLists) {
        if (nodeInfo.depth >= listOfLists.size()) {
            listOfLists.add(new LinkedList<BinaryTreeNode>());
        }
        listOfLists.get(nodeInfo.depth).add(nodeInfo.node);
    }

    @Test 
    public void threeNodeTree() {
        BinaryTreeNode bNode = BinaryTreeNode.build("B");
        BinaryTreeNode cNode = BinaryTreeNode.build("C");
        BinaryTreeNode root = BinaryTreeNode.build("A", bNode, cNode);
        Object listOfLists = listOfDepths(root);
        assertEq(listOfLists.toString(), "[[A], [B, C]]");
    }

    @Test
    public void sevenNodes() {
        BinaryTreeNode dNode = BinaryTreeNode.build("D");
        BinaryTreeNode eNode = BinaryTreeNode.build("E");
        BinaryTreeNode fNode = BinaryTreeNode.build("F");
        BinaryTreeNode gNode = BinaryTreeNode.build("G");
        BinaryTreeNode bNode = BinaryTreeNode.build("B", dNode, eNode);
        BinaryTreeNode cNode = BinaryTreeNode.build("C", fNode, gNode);
        BinaryTreeNode root = BinaryTreeNode.build("A", bNode, cNode);
        List<List<BinaryTreeNode>> listOfLists = listOfDepths(root);
        assertEq(listOfLists.toString(), "[[A], [B, C], [D, E, F, G]]");
    }
}

final class NodeInfo {
    final int depth;
    final BinaryTreeNode node;

    public NodeInfo(int depth, BinaryTreeNode node) {
        this.depth = depth;
        this.node = node;
    }
}

class BinaryTreeNode {
    String name = "";
    BinaryTreeNode left;
    BinaryTreeNode right;

    public String toString() {
        return name;
    }

    static BinaryTreeNode build(String name) {
        return build(name, null, null);
    }

    static BinaryTreeNode build(String name, BinaryTreeNode left, BinaryTreeNode right) {
        BinaryTreeNode node = new BinaryTreeNode();
        node.name = name;
        node.left = left;
        node.right = right;
        return node;
    }
}
