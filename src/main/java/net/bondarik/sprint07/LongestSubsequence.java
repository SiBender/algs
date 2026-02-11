package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LongestSubsequence {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());
        int[] data = new int[dataLength];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] dp = new int[dataLength];
        Arrays.fill(dp, 1);

        int maxLen = 1;

        for (int i = 1; i < dataLength; i++) {
            for (int j = 0; j < i; j++) {
                if (data[j] < data[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        System.out.println(maxLen);
        System.out.println(findIndexes(dp, maxLen));
    }

    private static String findIndexes(int[] dp, int maxLen) {
        if (maxLen == 1) {
            return "1";
        }

        int maxIndex = -1;

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLen) {
                maxIndex = i;
                break;
            }
        }

        Deque<Integer> indexes = new LinkedList<>();


        while (maxLen > 0 && maxIndex >= 0) {
            if (dp[maxIndex] == maxLen) {
                indexes.addFirst(maxIndex + 1);
                maxLen--;
            }
            maxIndex--;
        }

        return indexes.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
