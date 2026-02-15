package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GoldOfLepricons {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] split =  reader.readLine().split(" ");
        int dataLength = Integer.parseInt(split[0]);
        int totalCapacity = Integer.parseInt(split[1]);

        int[] gold = new int[dataLength];
        int[] dp = new int[totalCapacity + 1];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            gold[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(gold);

        for (int line = 0; line < dataLength; line++) {
            int currentGoldValue = gold[line];
            if (currentGoldValue > totalCapacity) {
                break;
            }

            int[] currentLineResult = new int[totalCapacity + 1];

            for (int capacity = 0; capacity < totalCapacity + 1; capacity++) {
                if (line == 0) {
                    currentLineResult[capacity] = capacity < currentGoldValue ? 0 : currentGoldValue;
                } else {
                    int prevBestValueIndex = capacity - currentGoldValue;
                    if (prevBestValueIndex < 0) {
                        currentLineResult[capacity] = dp[capacity];
                    } else {
                        int newValue = dp[prevBestValueIndex] + currentGoldValue;
                        if (newValue <= totalCapacity) {
                            currentLineResult[capacity] = Math.max(dp[capacity], newValue);
                        } else {
                            currentLineResult[capacity] = Math.max(dp[capacity], currentGoldValue);
                        }
                    }

                }
            }

            dp = currentLineResult;
        }

        System.out.println(dp[totalCapacity]);

    }
}
