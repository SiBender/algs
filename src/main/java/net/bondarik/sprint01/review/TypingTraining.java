package net.bondarik.sprint01.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TypingTraining {

    private static final int INPUT_ROWS_COUNT = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maxTypingCapacity = Integer.parseInt(reader.readLine()) * 2;

        int[] keysCounter = new int[10];

        for (int i = 0; i < INPUT_ROWS_COUNT; i++) {
            for (char currentKey : reader.readLine().toCharArray()) {
                if (currentKey != '.') {
                    int value = currentKey - '0';
                    keysCounter[value]++;
                }
            }
        }

        int successRounds = 0;
        for (int j : keysCounter) {
            if (j > 0 && j <= maxTypingCapacity) {
                successRounds++;
            }
        }

        System.out.println(successRounds);
    }
}
