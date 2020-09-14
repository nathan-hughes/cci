package cci.ch4;

import static cci.test.Asserts.*;
import java.util.*;
import cci.test.*;

public class Invert {

    public static void main(String[] args) {
        TestRunner.runAllTests(Invert.class);
    }

    public void invert(BinaryTreeNode n) {
        if (n == null) return;
        invert(n.left);
        invert(n.right);
        BinaryTreeNode t = n.left;
        n.left = n.right;
        n.right = t;
    }

    private BinaryTreeNode root = initializeTestData();

    static BinaryTreeNode initializeTestData() {
        BinaryTreeNode dNode = BinaryTreeNode.build("D");
        BinaryTreeNode eNode = BinaryTreeNode.build("E");
        BinaryTreeNode fNode = BinaryTreeNode.build("F");
        BinaryTreeNode gNode = BinaryTreeNode.build("G");
        BinaryTreeNode bNode = BinaryTreeNode.build("B", dNode, eNode);
        BinaryTreeNode cNode = BinaryTreeNode.build("C", fNode, gNode);
        BinaryTreeNode aNode = BinaryTreeNode.build("A", bNode, cNode);
        return aNode;    
    }

    @Test
    public void testInvert() {
        invert(root);
        assertEq(root.name, "A");
        assertEq(root.left.name, "C");
        assertEq(root.right.name, "B");
        assertEq(root.left.left.name, "G");
        assertEq(root.left.right.name, "F");
        assertEq(root.right.left.name, "E");
        assertEq(root.right.right.name, "D");
    }
 
}