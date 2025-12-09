package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoBikes {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        int[] nums = new int[dataLength];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int bikeCost = Integer.parseInt(reader.readLine());

        int oneBikePoint = findFirst(nums, bikeCost, 0, dataLength - 1);
        int twoBikesPoint = findFirst(nums, bikeCost * 2, 0, dataLength - 1);

        System.out.printf("%d %d", oneBikePoint, twoBikesPoint);
    }

    private static int findFirst(int[] array, int targetValue, int leftPointer, int rightPointer) {
        if (array[rightPointer] < targetValue) {
            return -1;
        }

        if (array[leftPointer] >= targetValue) {
            return leftPointer + 1;
        }

        if (rightPointer - leftPointer == 1) {
            return rightPointer + 1;
        }

        int middlePointer = (leftPointer + rightPointer) / 2;
        if (array[middlePointer] < targetValue) {
            return findFirst(array, targetValue, middlePointer, rightPointer);
        } else {
            return findFirst(array, targetValue, leftPointer, middlePointer);
        }
    }
}
