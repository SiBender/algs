package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class BubbleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        int[] data = new int[dataLength];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());


        for (int i = 0; i < dataLength; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());

        int swapCounter = 0;
        for (int i = 0; i < dataLength; i++) {
            boolean isSwapWasUsed = false;
            for (int j = 0; j < dataLength - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    isSwapWasUsed = true;
                    swapCounter++;
                }
            }

            if (isSwapWasUsed) {
                result.add(arrayToString(data));
            }
        }

        if (swapCounter == 0) {
            result.add(arrayToString(data));
        }

        System.out.println(result);
    }

    private static void swap(int[] data, int left, int right) {
        int tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    private static String arrayToString(int[] arr) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int item : arr) {
            stringJoiner.add(Integer.toString(item));
        }
        return stringJoiner.toString();
    }
}
