package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CommonSubsequence {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int firstLen = Integer.parseInt(reader.readLine());
        int[] first = new int[firstLen];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < firstLen; i++) {
            first[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int secondLen = Integer.parseInt(reader.readLine());
        int[] second = new int[secondLen];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < secondLen; i++) {
            second[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[][] dp = new int[firstLen + 1][secondLen + 1];

        for (int i = 1; i < firstLen + 1; i++) {
            for (int j = 1; j < secondLen + 1; j++) {
                dp[i][j] = (first[i - 1] == second[j - 1]) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        Deque<Integer> firstIndexes = new LinkedList<>();
        Deque<Integer> secondIndexes = new LinkedList<>();

        int firstIndex = firstLen;
        int secondIndex = secondLen;
        while (dp[firstIndex][secondIndex] != 0) {
            if (first[firstIndex - 1] == second[secondIndex - 1]) {
                firstIndexes.addFirst(firstIndex);
                secondIndexes.addFirst(secondIndex);
                firstIndex--;
                secondIndex--;
            } else {
                if (dp[firstIndex][secondIndex] == dp[firstIndex - 1][secondIndex]) {
                    firstIndex--;
                } else {
                    secondIndex--;
                }
            }
        }


        System.out.println(dp[firstLen][secondLen]);
        System.out.println(firstIndexes.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(secondIndexes.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
