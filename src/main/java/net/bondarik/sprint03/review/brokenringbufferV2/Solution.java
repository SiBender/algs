// https://contest.yandex.ru/contest/23815/run-report/153640488/

package net.bondarik.sprint03.review.brokenringbufferV2;

public class Solution {
    public static int brokenSearch(int[] arr, int k) {
        int lastElementValue = arr[arr.length - 1];

        int leftBorder = 0;
        int rightBorder = arr.length;
        int middle = 0;

        while (rightBorder - leftBorder > 1) {
            middle = (leftBorder + rightBorder) / 2;
            int middleValue = arr[middle];

            if (middleValue == k) {
                return middle;
            }

            if (k <= lastElementValue) { //ищем в правой части массива
                if (middleValue <= lastElementValue) {
                    if (middleValue > k) {
                        rightBorder = middle;
                    } else {
                        leftBorder = middle;
                    }
                } else {
                    leftBorder = middle;
                }
            } else { //ищем в левой части массива. k >= firstElementValue
                if (middleValue <= lastElementValue) {
                    rightBorder = middle;
                } else {
                    if (middleValue > k) {
                        rightBorder = middle;
                    } else {
                        leftBorder = middle;
                    }
                }
            }
        }

        return arr[leftBorder] == k ? leftBorder : -1;
    }

    private static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        assert 6 == brokenSearch(arr, 5);
    }

}