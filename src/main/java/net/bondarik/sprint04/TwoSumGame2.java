package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoSumGame2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int targetSum = Integer.parseInt(reader.readLine());

        int[] data = new int[dataLength];

        int pointer = 0;
        while (tokenizer.hasMoreTokens()) {
            data[pointer] = Integer.parseInt(tokenizer.nextToken());
            pointer++;
        }

        int left = 0;
        int right = dataLength - 1;
        int currentSum = 0;
        while (left < right) {
            currentSum = data[left] + data[right];
            if (currentSum == targetSum) {
                System.out.println(data[left] + " " + data[right]);
                return;
            } else {
                if (currentSum > targetSum) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println("None");
    }
}
