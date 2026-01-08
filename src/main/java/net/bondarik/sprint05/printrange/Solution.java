package net.bondarik.sprint05.printrange;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Solution {
    public static void main(String[] args) throws IOException {
        test();
    }

    public static void printRange(Node root, int L, int R, BufferedWriter writer) throws IOException {
        if (root.getLeft() != null && root.getValue() >= L) {
            printRange(root.getLeft(), L, R, writer);
        }

        if (root.getValue() >= L && root.getValue() <= R) {
            write(writer, root.getValue());
        }

        if (root.getRight() != null && root.getValue() <= R) {
            printRange(root.getRight(), L, R, writer);
        }

    }

    private static void write(BufferedWriter writer, int value) throws IOException {
        writer.write(value + System.lineSeparator());
    }

    // <template>
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    // <template>

    private static void test() throws IOException{
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(null, node1, 1);
        Node node3 = new Node(null, null, 8);
        Node node4 = new Node(null, node3, 8);
        Node node5 = new Node(node4, null, 9);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node2, node6, 5);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        printRange(node7, 2, 8, writer);
        writer.flush();
        // expected output: 2 5 8 8
    }
}
