package net.bondarik.sprint05.is_balanced;

public class Solution {
    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        }

        return Math.abs(getDepth(head.left) - getDepth(head.right)) <= 1
               && treeSolution(head.left)
               && treeSolution(head.right);
    }

    private static int getDepth(Node root) {
        if  (root == null) {
            return 0;
        }

        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
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
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
    }
}
