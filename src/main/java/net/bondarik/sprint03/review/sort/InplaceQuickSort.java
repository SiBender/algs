// https://contest.yandex.ru/contest/23815/run-report/153646171/

package net.bondarik.sprint03.review.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class InplaceQuickSort {
    private static final Random RANDOM = new Random();

    private static String[] names;
    private static int[] points;
    private static int[] penaltyPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        names = new String[dataLength];
        points = new int[dataLength];
        penaltyPoints = new int[dataLength];

        for (int i = 0; i < dataLength; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            names[i] = tokenizer.nextToken();
            points[i] = Integer.parseInt(tokenizer.nextToken());
            penaltyPoints[i] = Integer.parseInt(tokenizer.nextToken());
        }

        sort(0, dataLength - 1);

        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = names.length - 1; i >= 0; i--) {
            result.add(names[i]);
        }

        System.out.println(result);
    }

    private static void sort(int left, int right) {
        int leftPointer = left;
        int rightPointer = right;
        int baseValuePoint;

        if (right - left <= 1) {
            int compareResult = compare(left, right);
            if (compareResult > 0) {
                swap(left, right);
            }
            return;
        } else {
            baseValuePoint = RANDOM.nextInt(right - left) + left;

            while (leftPointer < rightPointer) {
                int leftCompareResult = compare(leftPointer, baseValuePoint);
                if (leftCompareResult <= 0) {
                    leftPointer++;
                } else {
                    int rightCompareResult = 1;
                    while (rightPointer > leftPointer && rightCompareResult > 0) {
                        rightCompareResult = compare(rightPointer, baseValuePoint);
                        if (rightCompareResult > 0) {
                            rightPointer--;
                        }
                    }

                    if (rightPointer == baseValuePoint) {
                        baseValuePoint = leftPointer;
                    }
                    swap(leftPointer, rightPointer);
                }
            }
        }

        if (compare(leftPointer, baseValuePoint) > 0) {
            leftPointer--;
        }

        sort(left, leftPointer);

        if (leftPointer < right) {
            sort(leftPointer + 1, right);
        }

    }

    private static int compare(int left, int right) {
        if (left == right) {
            return 0;
        }
        int comparePoints = Integer.compare(points[left], points[right]);
        if (comparePoints != 0) {
            return comparePoints;
        }

        int comparePenaltyPoints = Integer.compare(penaltyPoints[left], penaltyPoints[right]);
        if (comparePenaltyPoints != 0) {
            return comparePenaltyPoints * -1;
        }

        return names[left].compareTo(names[right]) * -1;
    }

    private static void swap(int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        String tmp = names[index1];
        names[index1] =  names[index2];
        names[index2] = tmp;

        int tmpInt = points[index1];
        points[index1] = points[index2];
        points[index2] = tmpInt;

        tmpInt = penaltyPoints[index1];
        penaltyPoints[index1] = penaltyPoints[index2];
        penaltyPoints[index2] = tmpInt;
    }
}
