package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class NeighborsMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        int[][]matrix = new int[vertexCount][vertexCount];

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            matrix[from - 1][to - 1] = 1;
        }

        System.out.println(print(matrix));
    }

    private static String print(int[][] matrix) {
        StringJoiner lines = new StringJoiner(System.lineSeparator());
        for (int[] line : matrix) {
            StringJoiner row = new StringJoiner(" ");
            for (int i : line) {
                row.add(String.valueOf(i));
            }
            lines.add(row.toString());
        }

        return lines.toString();
    }
}
