package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringJoiner;

public class FlowersWithPath {
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

        int line = 0;
        int column = m;

        Stack<String> result = new Stack<>();
        while (line != n - 1 || column != 1) {
            if (line == n - 1) {
                result.push("R");
                column--;
                continue;
            }
            if (column == 1) {
                result.push("U");
                line++;
                continue;
            }

            if (dp[line + 1][column] > dp[line][column - 1]) {
                result.push("U");
                line++;
            } else {
                result.push("R");
                column--;
            }
        }

        System.out.println(dp[0][m]);
        System.out.println(getPath(result));
    }

    private static String getPath(Stack<String> path) {
        StringJoiner result = new StringJoiner("");
        while (!path.isEmpty()) {
            result.add(path.pop());
        }

        return result.toString();
    }
}
