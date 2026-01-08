package net.bondarik.sprint05.sift_down;

public class Solution {
    public static int siftDown(int[] heap, int idx) {
        int value = heap[idx];
        int leftIdx = idx * 2;
        int rightIdx = idx * 2 + 1;

        if (leftIdx < heap.length && rightIdx < heap.length) {
            if (value > heap[leftIdx] && value > heap[rightIdx]) {
                return idx;
            } else {
                if (heap[leftIdx] < heap[rightIdx]) {
                    swap(heap, rightIdx, idx);
                    return siftDown(heap, rightIdx);
                } else {
                    swap(heap, leftIdx, idx);
                    return siftDown(heap, leftIdx);
                }
            }
        } else {
            if (leftIdx < heap.length && heap[leftIdx] > value) {
                swap(heap, leftIdx, idx);
                return siftDown(heap, leftIdx);
            } else if (rightIdx < heap.length && heap[rightIdx] > value) {
                swap(heap, rightIdx, idx);
                return siftDown(heap, rightIdx);
            } else {
                return idx;
            }
        }

    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }
}
