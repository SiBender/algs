package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThrashIndexes {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        int[] arr = new int[dataLength];

        StringTokenizer tokenizer  = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i <= 11; i++) {
            System.out.println(i + " | " + countPairs(arr, i));
        }

        long k = Long.parseLong(reader.readLine());
        int maxDelta = arr[arr.length - 1] - arr[0];

        System.out.println(getDelta(arr, k, 0, maxDelta));

    }

    private static int getDelta(int[] arr, long target, int left, int right) {
        System.out.println(left + " | " + right);
        if (right - left <= 1) {
            long leftCount = countPairs(arr, left);
            if (leftCount >= target) {
                return left;
            }
            return right;
        }

        int middle = (left + right) / 2;

        long pairsCount = countPairs(arr, middle);


        if (pairsCount < target) {
            return getDelta(arr, target, middle, right);
        } else {
            return getDelta(arr, target, left, middle);
        }
    }

    private static long countPairs(int[] arr, int limit) {
        long count = 0;

        int left = 0;
        int right = 0;
        int lastCountedRight = right;

        while (right < arr.length) {
            if (left == right) {
                right++;
            } else {
                if (arr[right] - arr[left] <= limit && lastCountedRight != right) {
                    //считаем, что для правого элемента еще не добавлено ни одной разности
                    int numsInRange = right - left + 1;
                    count += numsInRange - 1;
                    lastCountedRight = right;
                    right++;
                } else {
                    left++;
                }
            }
        }

        return count;
    }
}
