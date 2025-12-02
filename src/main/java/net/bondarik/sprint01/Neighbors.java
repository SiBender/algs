package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Neighbors {
    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int height = Integer.parseInt(reader.readLine().strip());
        int width = Integer.parseInt(reader.readLine().strip());

        String[] matrix = new String[height];

        for (int i = 0; i < height; i++) {
            matrix[i] = reader.readLine();
        }

        int line = Integer.parseInt(reader.readLine().strip());
        int column = Integer.parseInt(reader.readLine().strip());

        addLeftNeighbor(matrix, line, column, height, width);
        addRightNeighbor(matrix, line, column, height, width);
        addUpNeighbor(matrix, line, column, height, width);
        addDownNeighbor(matrix, line, column, height, width);

        System.out.println(result.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));;
    }

    private static void addLeftNeighbor(String[] matrix, int x, int y, int height, int width) {
        if (y > 0) {
            result.add(Integer.parseInt(extractNumsArray(matrix[x])[y - 1]));
        }
    }

    private static void addRightNeighbor(String[] matrix, int x, int y, int height, int width) {
        if (y + 1 < width) {
            result.add(Integer.parseInt(extractNumsArray(matrix[x])[y + 1]));
        }
    }

    private static void addUpNeighbor(String[] matrix, int x, int y, int height, int width) {
        if (x > 0) {
            result.add(Integer.parseInt(extractNumsArray(matrix[x - 1])[y]));
        }
    }

    private static void addDownNeighbor(String[] matrix, int x, int y, int height, int width) {
        if (x + 1 < height) {
            result.add(Integer.parseInt(extractNumsArray(matrix[x + 1])[y]));
        }
    }

    private static String[] extractNumsArray(String input) {
        return input.split(" ");
    }
}
