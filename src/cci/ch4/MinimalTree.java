package cci.ch4;

import static cci.test.Asserts.*;
import java.util.*;
import cci.test.*;

public class MinimalTree {

    public static void main(String ... args) {
        TestRunner.runAllTests(MinimalTree.class);
    }

    public static BinarySearchTreeNode minimalTree(int[] input) {
        return minimalTree(input, 0, input.length - 1);
    }

    public static BinarySearchTreeNode minimalTree(
        int[] input, int start, int end) {
        if (end < start) {
           return null;
        }
        int midpoint = (start + end) / 2;
        BinarySearchTreeNode node = new BinarySearchTreeNode();
        node.value = input[midpoint];
        node.left = minimalTree(input, start, midpoint - 1);
        node.right = minimalTree(input, midpoint + 1, end);
        return node;
    }

    @Test
    public void sizeOneInput() {
        int[] input = new int []{1};
        BinarySearchTreeNode root = minimalTree(input);
        assertEq(root.value, 1);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    public void sizeThreeInput() {
        int[] input = new int []{1,2,3};
        BinarySearchTreeNode root = minimalTree(input);
        assertNull(root.right.right);        
        assertEq(root.right.value, 3);
        assertNull(root.right.left);
        assertEq(root.value, 2);
        assertNull(root.left.right);
        assertEq(root.left.value, 1);
        assertNull(root.left.left);
    }

    @Test
    public void sizeNineInput() {
        int[] input = new int []{1,2,3,4,5,6,7,8,9};
        BinarySearchTreeNode root = minimalTree(input);
        assertEq(root.right.right.right.value, 9);
        assertEq(root.right.right.value, 8);
        assertEq(root.right.value, 7);
        assertEq(root.right.left.value, 6);
        assertEq(root.value, 5);
        assertEq(root.left.right.right.value, 4);
        assertEq(root.left.right.value, 3);
        assertEq(root.left.value, 2);
        assertEq(root.left.left.value, 1);
    }    
} 

class BinarySearchTreeNode {
    int value;
    BinarySearchTreeNode left;
    BinarySearchTreeNode right;
}