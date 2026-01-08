package net.bondarik.sprint05.max_path;

public class Solution {
    public static void main(String[] args) {
        test();
    }

    public static int treeSolution(Node head) {
        if (head == null) {
            return 0;
        }

        return getMaxCombination(treeSolution(head.left), treeSolution(head.right), head.value);
    }

    private static int getMaxCombination(int left, int right, int self) {
        int result = 0;

        if (self > 0) {
            result += self;
        }

        if (left > 0) {
            result += left;
        }

        if (right > 0) {
            result += right;
        }

        return result;
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


    private static void test() {
        Node node1 = new Node(5, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(-3, node2, node1);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(2, node4, node3);
        int qwe = treeSolution(node5);
        assert treeSolution(node5) == 6;
    }
}
