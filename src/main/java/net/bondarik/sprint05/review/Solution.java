package net.bondarik.sprint05.review;

// <template>
public class Solution {
    public static Node remove(Node root, int key) {
        Node parentOfNodeToDelete = findParentOfNodeWithValue(root, key);
        if (parentOfNodeToDelete == null) {
            return null;
        }


        Node replacingNode = extractLeftBottomNode(root, root.getRight());
        if (replacingNode == null) {
            replacingNode = extractRightBottomNode(root);
        }

        if (replacingNode == null) {
            if (parentOfNodeToDelete.getLeft() != null && parentOfNodeToDelete.getLeft().getValue() == key) {
                parentOfNodeToDelete.setLeft(null);
            } else {
                parentOfNodeToDelete.setRight(null);
            }

            return root;
        }

        Node deletedNode = findNode(parentOfNodeToDelete, key);

        boolean nodeToDeleteIsLeft = key < parentOfNodeToDelete.getValue();

        return null;
    }

    private static Node extractLeftBottomNode(Node parent, Node root) {
        if (root == null) { // нет левого потомка
            return null;
        } else {
            if (root.getLeft() == null) {
                root.setLeft(null);

                return root;
            } else {
                return extractLeftBottomNode(root, root.getLeft());
            }
        }
    }

    private static Node extractRightBottomNode(Node root) {
        if (root.getRight() == null) { // нет правого потомка
            return null;
        } else {
            Node rightChild = root.getRight();
            if (rightChild.getRight() != null) {
                return extractRightBottomNode(rightChild);
            } else {
                root.setRight(null);
                return rightChild;
            }
        }
    }

    private static Node findParentOfNodeWithValue(Node head, int valueToFind) {
        if (head == null || head.getValue() == valueToFind) {
            return null;
        }

        if (valueToFind < head.getValue()) {
            if (head.getLeft() != null && head.getLeft().getValue() == valueToFind) {
                return head;
            } else {
                return findParentOfNodeWithValue(head.getLeft(), valueToFind);
            }
        } else {
            if (head.getRight() != null && head.getRight().getValue() == valueToFind) {
                return head;
            } else {
                return findParentOfNodeWithValue(head.getRight(), valueToFind);
            }
        }
    }

    private static Node findNode(Node root, int key) {
        if (root.getValue() == key) {
            return root;
        }

        if (key < root.getValue()) {
            return findNode(root.getLeft(), key);
        } else {
            return findNode(root.getRight(), key);
        }
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }
}


// <template>
class Node {
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
