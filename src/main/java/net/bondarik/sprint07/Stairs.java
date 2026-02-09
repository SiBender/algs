package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stairs {
    private static final int module = 1_000_000_000 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        int stairsCount = Integer.parseInt(input[0]);
        int maxJump = Integer.parseInt(input[1]);

        int[] dp = new int[stairsCount + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            for (int j = i - 1; j >= 0 && i - j <= maxJump; j--) {
                dp[i] = (dp[i] + dp[j]) % module;
            }
        }

        System.out.println(dp[stairsCount]);
    }
}
