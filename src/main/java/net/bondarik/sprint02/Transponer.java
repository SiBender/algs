package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Transponer {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputLinesCount = Integer.parseInt(reader.readLine());
        int inputColumnsCount = Integer.parseInt(reader.readLine());

        int[][] output = new int[inputColumnsCount][inputLinesCount];

        for (int i = 0; i < inputLinesCount; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < inputColumnsCount; j++) {
                output[j][i] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int line = 0; line < output.length; line++) {
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int value : output[line]) {
                stringJoiner.add(String.valueOf(value));
            }
            System.out.println(stringJoiner);
        }
    }
}
