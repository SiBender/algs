package net.bondarik.sprint03.mergesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int[] merge(int[] arr, int left, int mid, int right) {
        int leftPointer = left;
        int rightPointer = mid;

        List<Integer> merged = new ArrayList<>(right - left);

        while (leftPointer < mid && rightPointer < right) {
            if (arr[leftPointer] <= arr[rightPointer]) {
                merged.add(arr[leftPointer]);
                leftPointer++;
            } else {
                merged.add(arr[rightPointer]);
                rightPointer++;
            }
        }

        while (leftPointer < mid) {
            merged.add(arr[leftPointer]);
            leftPointer++;
        }
        while (rightPointer < right) {
            merged.add(arr[rightPointer]);
            rightPointer++;
        }

        replace(arr, merged, left);

        return arr;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        int middle = (left + right) / 2;
        if (right - left <= 2) {
            merge(arr, left, (left + right) / 2, right);
        } else {
            merge_sort(arr, left, middle);
            merge_sort(arr, middle, right);
            merge(arr, left, middle, right);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        assert Arrays.equals(b, expected);
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        assert Arrays.equals(c, expected2);
    }

    private static void replace(int[] data, List<Integer> newValues, int startPos) {
        for (int val : newValues) {
            data[startPos] = val;
            startPos++;
        }
    }

}
