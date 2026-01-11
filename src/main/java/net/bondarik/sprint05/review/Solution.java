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
 4) Потомков удаленного элемента присваиваем его "преемнику"

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 Все вспомогательные методы строятся вокруг пары:
    узел дерева - родитель из которого мы пришли в этот узел.

 1) Поиск родителя удаляемого узла.
 Это простой поиск по BST, просо в процессе поиска мы дополнительно запоминаем узел из которого пришли в текущую ноду.

 При совпадении возвращаем родителя
 Если узел не найден или удаляемое значение в корне дерева - возвращаем null


 2) Выбор элемента который необходимо переместить на место удаленного
 для сохранения корректной структуры двоичного дерева поиска, необходимо удаляемый узел заменить на узел
 со значением ближайшим слева или справа
 Т.е. либо максимальный в левом поддереве, либо минимальный в правом поддереве, относительно удаляемого узла.

 Разберем работу на примере поиска максимума в левом поддереве
 Максимальный элемент в BST зранится в нижнем правом элементе, это свойство самого BST.
 поэтому метод называется extractRightBottomNode()

 Мы постоянно выбираем правого потомка до тех пор, пока не найдем узел у которого правого потомка нет.
 Это и будет нашим максимумом.

 Теперь важно учесть, что у найденного узла может быть левый потомок.
 Благодаря тому, что мы храним информацию об узле из которого пришли,
 достаточно поменять ссылку в родителе.
 Тут возникает важный нюанс.
 При запуске поиска нижнего правого элемента мы идем в левое поддерево.
 Поэтому на первом шаге алгоритм проходит по маршруту "влево -> вправо",
 а на всех последующих "вправо -> вправо"
 Поэтому при переносе возможного левого потомка нужно дополнительно проверять
 в какую из ветвей прописывать ссылку в родителе.

 Теперь можно возвращать результат.

 Поиск минимума в левом поддереве работает аналогично, просто в противоположных направлениях. \
 (Все Лево и Право меняются местами)

 Сначала ищем в левом поддереве, и если там пусто, то ищем в правом.

 3) Теперь непосредственно замена удаляемого узла.
 У нас на руках есть ссылка на родителя удаляемого узла.
 Ссылка на удаляемых узел
 И узел для вставки на место удаленного.

 Необходимо определить левым или правым потомком был удаляемый узел и заменить соответствующую ссылку.
 После этого новому узлу присвоить всех потомков из удаленного узла.

 После этого структура дерева будет корректно перестроена.

 Частные случаи, которые обработаны в решении.
 1) поиск родителя удаляемого узла вернул null
 Это означает, что
        а) искомого значения нет в дереве - просто возвращаем исходное дерево
        б) удаляется корень - тогда нужно по обычному алгоритму найти узел для замены и т.д.

 2) нет узла для замены. У удаляемого узла не было потомков.
 Тогда просто обнуляется ссылка в родителе. После этого структура дерева остается корректной. Возвращаем корень.


 В общем случае мы производим перемещение 2 узлов дерева.
 Корректность алгоритма основана на том, что при выполнении каждого из них корректно меняются ссылки в родителях
 И корректно пересохраняются потомки перемещаемых узлов.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 Временная сложность складывается из
 1) поиска удаляемого узла = O(h), где h - высота дерева
 2) поиска узла для подмены удаляемого = O(h).
 Хоть алгоритм и ищет по очереди сначала в левом потом в правом поддереве,
 фактически поиск будет происходить только в одном таком дереве.
 Т.к. если поиск по левому поддереву вернул null, значит само поддерево пустое.

 Итого O(h) + O(h) = O(h)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Память выделяется для хранения нескольких переменных.
 Это сводится к оценке O(1)

 Дополнительно нужно учесть затраты на стек при рекурсивном поиске по дереву.
 В худшем случае поиск займет h шагов.
 Где h - высота дерева.

 Итого общая пространственная сложность O(h)
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
