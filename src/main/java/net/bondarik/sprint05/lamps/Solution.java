package net.bondarik.sprint05.lamps;

public class Solution {
    public static int treeSolution(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }

        int childrenMax = head.value;
        int leftMax = childrenMax;
        int rightMax = childrenMax;
        if (head.left != null) {
            leftMax = treeSolution(head.left);
        }

        if (head.right != null) {
            rightMax = treeSolution(head.right);
        }

        childrenMax = Math.max(leftMax, rightMax);
        return Math.max(head.value, childrenMax);
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    // <template>


    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}
