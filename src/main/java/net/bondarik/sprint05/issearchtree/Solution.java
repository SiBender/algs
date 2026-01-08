package net.bondarik.sprint05.issearchtree;

public class Solution {
    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        }
        boolean isCorrectChildren =
                (head.left == null || getMax(head.left) < head.value) &&
                (head.right == null || getMax(head.right) > head.value);

        return isCorrectChildren && treeSolution(head.left) && treeSolution(head.right);
    }

    public static int getMax(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }

        int childrenMax = head.value;
        int leftMax = childrenMax;
        int rightMax = childrenMax;
        if (head.left != null) {
            leftMax = getMax(head.left);
        }

        if (head.right != null) {
            rightMax = getMax(head.right);
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }
}
