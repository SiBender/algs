package net.bondarik.sprint05.split_tree;

import java.util.ArrayList;
import java.util.List;


public class Solution {

    public static void main(String[] args) {
        test2();
    }

    public static List<Node> split(Node root, int k) {
        if (k == 0) {
            return createPair(null, root);
        }
        if (root.getSize() == k) {
            return createPair(root, null);
        }

        int leftSize = root.getLeft() != null ? root.getLeft().getSize() : 0;
        if (leftSize >= k) {
            Node left = root.getLeft();
            List<Node> leftParents = unlinkLeft(root);
            merge(leftParents, split(left, k));
            return leftParents;
        } else {
            int newK = k - leftSize - 1;

            Node right = root.getRight();
            List<Node> rightParents = unlinkRight(root);

            merge(rightParents, split(right, newK));
            return rightParents;
        }
    }

    private static List<Node> unlinkLeft(Node root) {
        Node leftParent = root.getLeft();
        Node rightParent = root;
        if (leftParent != null) {
            rightParent.setSize(rightParent.getSize() - leftParent.getSize());
        }
        rightParent.setLeft(null);
        return createPair(null, rightParent);
    }

    private static List<Node> unlinkRight(Node root) {
        Node leftParent = root;
        Node rightParent = root.getRight();

        if (rightParent != null) {
            leftParent.setSize(leftParent.getSize() - rightParent.getSize());
        }

        leftParent.setRight(null);
        return createPair(leftParent, null);
    }

    private static void merge(List<Node> parents, List<Node> children) {
        parents.set(0, mergeNodes(parents.get(0), children.get(0)));
        parents.set(1, mergeNodes(parents.get(1), children.get(1)));
    }

    private static Node mergeNodes(Node parent, Node child) {
        if (parent == null) {
            return child;
        }

        if (child != null && parent != child) {
            if (child.getValue() < parent.getValue()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }

            parent.setSize(parent.getSize() + child.getSize());
        }
        return parent;
    }

    private static List<Node> createPair(Node left, Node right) {
        List<Node> pair = new ArrayList<>();
        pair.add(left);
        pair.add(right);
        return pair;
    }

    // <template>
    private static class Node {

        private Node left;
        private Node right;
        private int value;
        private int size;

        Node(Node left, Node right, int value, int size) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.size = size;
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
    // <template>


    public static void test() {
        Node node1 = new Node(null, null, 3, 1);
        Node node2 = new Node(null, node1, 2, 2);
        Node node3 = new Node(null, null, 8, 1);
        Node node4 = new Node(null, null, 11, 1);
        Node node5 = new Node(node3, node4, 10, 3);
        Node node6 = new Node(node2, node5, 5, 6);
        List<Node> res = split(node6, 4);
        assert res.get(0).getSize() == 4;
        assert res.get(1).getSize() == 2;
    }

    public static void test2() {
        Node node10 = new Node(null, null, 932, 1);
        Node node9 = new Node(null, node10, 912, 2);
        Node node8 = new Node(null, null, 822, 1);
        Node node7 = new Node(node8, node9, 870, 4);
        Node node6 = new Node(null, null, 701, 1);
        Node node5 = new Node(node6, node7, 702, 6);

        Node node4 = new Node(null, null, 266, 1);
        Node node3 = new Node(null, node4, 191, 2);
        Node node2 = new Node(node3, null, 298, 3);
        Node node1 = new Node(node2, node5, 668, 10);


        List<Node> res = split(node1, 1);
        int a = res.get(0).getSize(); // 1
        int b = res.get(1).getSize(); // 9
    }
}