package net.bondarik.sprint05.sift_up;

public class Solution {
    public static int siftUp(int[] heap, int idx) {
        int value = heap[idx];
        int parentIdx = idx / 2;

        if (parentIdx >= 1 && value > heap[parentIdx]) {
            swap(heap, parentIdx, idx);
            return siftUp(heap, parentIdx);
        } else {
            return idx;
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }
}
