// https://contest.yandex.ru/contest/24810/run-report/154912922/


/**
 -- ПРИНЦИП РАБОТЫ --
 1) Находим РОДИТЕЛЯ элемента, который планируем удалить
 Это нужно, так как фактически удаление это замена ссылки на удаляемый элемент.
 Она хранится в единственном месте - в родительском узле

 2) Выбираем узел дерева, который будет поставлен вместо удаленного
 Сначала ищем максимальный в левом поддереве, если его нет,
 то минимальный в правом

 т.к. выбранный элемент может иметь потомка, необходимо его не потерять и поставить на место выбранного элемента

 3) У родителя меняем удаленного потомка на элемент, выбранный на шаге 2.
 4) Потомков удаленного элемента присваиваем его "ПРЕЕМНИКУ"

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

 */

package net.bondarik.sprint05.review;

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
// <template>

public class Solution {
    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }

        Node parentOfNodeToDelete = findParentOfNodeWithValue(null, root, key);
        if (parentOfNodeToDelete == null) {
            //нужно удалить корень или такого значения нет

            if (root.getValue() != key) {
                return root;
            }

            Node newRoot;
            if (root.getLeft() != null) {
                newRoot = extractRightBottomNode(root, root.getLeft());
            } else {
                newRoot = extractLeftBottomNode(root, root.getRight());
            }

            if (newRoot == null) {
                return null;
            }

            newRoot.setLeft(root.getLeft());
            newRoot.setRight(root.getRight());
            return newRoot;
        }

        Node nodeToRemove = key < parentOfNodeToDelete.getValue() ?
                parentOfNodeToDelete.getLeft() : parentOfNodeToDelete.getRight();

        Node replacingNode;

        if (nodeToRemove.getLeft() != null) {
            replacingNode = extractRightBottomNode(nodeToRemove, nodeToRemove.getLeft());
        } else {
            replacingNode = extractLeftBottomNode(nodeToRemove, nodeToRemove.getRight());
        }

        boolean nodeToDeleteIsLeft = key < parentOfNodeToDelete.getValue();

        if (replacingNode == null) {
            if (nodeToDeleteIsLeft) {
                parentOfNodeToDelete.setLeft(null);
            } else {
                parentOfNodeToDelete.setRight(null);
            }
            return root;
        }

        if (nodeToDeleteIsLeft) {
            parentOfNodeToDelete.setLeft(replacingNode);
        } else {
            parentOfNodeToDelete.setRight(replacingNode);
        }

        replacingNode.setLeft(nodeToRemove.getLeft());
        replacingNode.setRight(nodeToRemove.getRight());

        return root;
    }

    private static Node extractLeftBottomNode(Node parent, Node child) {
        if (child == null) { // нет левого потомка
            return null;
        } else {
            if (child.getLeft() == null) {
                if (child.getValue() < parent.getValue()) {
                    parent.setLeft(child.getRight());
                } else {
                    parent.setRight(child.getRight());
                }

                return child;
            } else {
                return extractLeftBottomNode(child, child.getLeft());
            }
        }
    }

    private static Node extractRightBottomNode(Node parent, Node child) {
        if (child == null) { // нет правого потомка
            return null;
        } else {
            if (child.getRight() != null) {
                return extractRightBottomNode(child, child.getRight());
            } else {
                if (child.getValue() < parent.getValue()) {
                    parent.setLeft(child.getLeft());
                } else {
                    parent.setRight(child.getLeft());
                }

                return child;
            }
        }
    }

    private static Node findParentOfNodeWithValue(Node parent, Node child, int valueToFind) {
        if (child == null) {
            return null;
        }

        if (child.getValue() == valueToFind) {
            return parent;
        }

        if (valueToFind < child.getValue()) {
            return findParentOfNodeWithValue(child, child.getLeft(), valueToFind);
        } else {
            return findParentOfNodeWithValue(child, child.getRight(), valueToFind);
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
