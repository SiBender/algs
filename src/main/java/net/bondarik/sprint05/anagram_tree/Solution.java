package net.bondarik.sprint05.anagram_tree;

public class Solution {
    public static boolean treeSolution(Node head) {
        return isMirroredTwins(head.left, head.right);
    }

    private static boolean isMirroredTwins(Node head1, Node head2) {
        if (head1 == null) {
            return head2 == null;
        } else if (head2 == null) {
            return false;
        } else {
            return head1.value == head2.value
                    && isMirroredTwins(head1.left, head2.right)
                    && isMirroredTwins(head1.right, head2.left);
        }
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
        Node node1 = new Node(3,  null,  null);
        Node node2 = new Node(4,  null,  null);
        Node node3 = new Node(4,  null,  null);
        Node node4 = new Node(3,  null,  null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        assert treeSolution(node7);
    }
}
