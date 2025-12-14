package net.bondarik.sprint03.review.brokenringbuffer;

public class Solution {
    public static int brokenSearch(int[] arr, int k) {
        int lastElementValue = arr[arr.length - 1];
        int firstElementValue = arr[0];

        int leftBorder = 0;
        int rightBorder = arr.length;
        int searchPoint = (leftBorder + rightBorder) / 2;

        if (k >= firstElementValue) {
            //ищем в левой половине число, которое >= k
            while (arr[searchPoint] < k) {
                if (rightBorder - leftBorder <= 1) {
                    return getResultForCompassionPoints(arr, k, leftBorder, rightBorder);
                }

                if (arr[searchPoint] < firstElementValue) { // проверяем, что мы в пределах левой части
                    rightBorder = searchPoint;
                    searchPoint = (searchPoint + leftBorder) / 2;
                } else {
                    leftBorder = searchPoint;
                    searchPoint = (searchPoint + rightBorder) / 2;
                }
            }
            return binarySearch(arr, k, 0, searchPoint + 1); // + 1???
        } else {
            //ищем в правой половине число, которое <= k
            while (arr[searchPoint] > k) { // проверяем, что мы в пределах правой части
                if (rightBorder - leftBorder <= 1) {
                    return getResultForCompassionPoints(arr, k, leftBorder, rightBorder);
                }

                if (arr[searchPoint] > lastElementValue) {
                    leftBorder = searchPoint;
                    searchPoint = (searchPoint + rightBorder) / 2;
                } else {
                    rightBorder = searchPoint;
                    searchPoint = (searchPoint + leftBorder) / 2;
                }
            }
            return binarySearch(arr, k, searchPoint, arr.length);
        }
    }

    private static int binarySearch(int[] arr, int target, int left, int right) {
        if (right - left <= 1) {
            return getResultForCompassionPoints(arr, target, left, right);
        }

        int middle = (left + right) / 2;
        if (arr[middle] == target) {
            return middle;
        }

        if (arr[middle] > target) {
            return binarySearch(arr, target, left, middle);
        } else {
            return binarySearch(arr, target, middle, right);
        }
    }

    private static int getResultForCompassionPoints(int[] data, int target, int left, int right) {
        if (data[left] == target) {
            return left;
        } else if (right < data.length && data[right] == target) {
            return right;
        } else {
            return -1;
        }
    }

    private static void test() {
        /*int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        int qwe = brokenSearch(arr, 90);*/
        int[] arr = {3, 6, 7};
        int qwe = brokenSearch(arr, 8);

        assert 6 == brokenSearch(arr, 5);
    }

    public static void main(String[] args) {
        test();
    }
}