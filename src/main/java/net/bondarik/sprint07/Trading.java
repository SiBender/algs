package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Trading {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] data = new int[dataLength];

        for (int i = 0; i < dataLength; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println(getMaxWin(data));
    }

    private static int getMaxWin(int[] data) {
        if (data.length < 2) {
            return 0;
        }

        int buyPoint = -1;
        int sellPoint = -1;
        int result = 0;
        while (true) {
            buyPoint = getNextBuyPointIndex(data, sellPoint);
            if (buyPoint == -1) {
                break;
            }

            sellPoint = getNextSellPointIndex(data, buyPoint);
            if (sellPoint == -1) {
                break;
            }

            result = result - data[buyPoint] + data[sellPoint];
        }

        return result;
    }

    private static int getNextBuyPointIndex(int[] data, int lastSellIndex) {
        int pointer = lastSellIndex + 1;
        while (pointer < data.length - 1) {
            if (data[pointer] < data[pointer + 1]) {
                return pointer;
            }
            pointer++;
        }

        return -1;
    }

    private static int getNextSellPointIndex(int[] data, int lastBuyIndex) {
        int pointer = lastBuyIndex + 1;
        while (pointer < data.length - 1) {
            if (data[pointer] > data[pointer + 1]) {
                return pointer;
            }
            pointer++;
        }

        if (data[pointer] > data[lastBuyIndex]) {
            return pointer;
        }

        return -1;
    }
}
