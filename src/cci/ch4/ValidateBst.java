package cci.ch4;

import static cci.test.Asserts.*;
import cci.test.*;
import java.util.*;

public class ValidateBst {

    public static void main(String ... args) {
        TestRunner.runAllTests(ValidateBst.class);
    }

    public boolean validateBst(Node node) {
        return checkBstOrder(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

        
    public boolean checkBstOrder(Node n, int min, int max) {
        if (n == null) return true;

        if (n.value <= min || n.value > max) return false;

        if (!checkBstOrder(n.left, min, n.value)) return false;

        if (!checkBstOrder(n.right, n.value, max)) return false;

        return true;
    }

    @Test
    public void testBst() {
        Node fNode = Node.build(20, null, null);
        Node eNode = Node.build(5, null, null);
        Node dNode = Node.build(10, null, null);
        Node cNode = Node.build(16, dNode, fNode);
        Node bNode = Node.build(4, null, eNode);
        Node aNode = Node.build(8, bNode, cNode);
        assertTrue(validateBst(aNode)); 
    }

    @Test
    public void sad() {
        Node gNode = Node.build(1000, null, null);
        Node fNode = Node.build(20, null, null);
        Node eNode = Node.build(5, null, null);
        Node dNode = Node.build(10, null, null);
        Node cNode = Node.build(16, dNode, fNode);
        Node bNode = Node.build(4, gNode, eNode);
        Node aNode = Node.build(8, bNode, cNode);
        assertFalse(validateBst(aNode)); 
    }

    @Test
    public void testNonBst() {
        Node eNode = Node.build(3, null, null);
        Node dNode = Node.build(10, null, null);
        Node cNode = Node.build(16, dNode, null);
        Node bNode = Node.build(4, null, eNode);
        Node aNode = Node.build(8, bNode, cNode);       
        assertFalse(validateBst(aNode)); 
    }

    @Test
    public void duplicateOnLeftShouldBeOk() {
        Node gNode = Node.build(5, null, null);
        Node fNode = Node.build(20, null, null);
        Node eNode = Node.build(5, gNode, null);
        Node dNode = Node.build(10, null, null);
        Node cNode = Node.build(16, dNode, fNode);
        Node bNode = Node.build(4, null, eNode);
        Node aNode = Node.build(8, bNode, cNode);
        assertTrue(validateBst(aNode));
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public static Node build(int value, Node left, Node right) {
            Node n = new Node();
            n.value = value;
            n.left = left;
            n.right = right;
            return n;
        }
    }

}
