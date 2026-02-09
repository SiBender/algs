package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Flowers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] dimensions = reader.readLine().split(" ");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            char[] chars = reader.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                dp[i][j + 1] = chars[j] - '0';
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]) + dp[i][j];
            }
        }

        System.out.println(dp[0][m]);
    }
}
