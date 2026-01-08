package net.bondarik.sprint05.digits_path;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int treeSolution(Node head) {
        List<Integer> result = new ArrayList<>();

        getAllPaths(head, 0, result);

        return result.stream().mapToInt(Integer::intValue).sum();
    }

    private static void getAllPaths(Node head, int prefixSum, List<Integer> results) {
        if (head == null) {
            return;
        }

        prefixSum = prefixSum * 10 + head.value;

        if (head.left == null && head.right == null) {
            results.add(prefixSum);
        } else {
            getAllPaths(head.left, prefixSum, results);
            getAllPaths(head.right, prefixSum, results);
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
        Node node1 = new Node(2, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);
        assert treeSolution(node5) == 275;
    }
}
