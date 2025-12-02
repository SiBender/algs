package net.bondarik.sprint01.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TypingTraining {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maxTypingCapacity = Integer.parseInt(reader.readLine()) * 2;

        int[] keysCounter = new int[10];

        for (int i = 0; i < 4; i++) {
            for (char currentKey : reader.readLine().toCharArray()) {
                if (currentKey != '.') {
                    int value = currentKey - '0';
                    keysCounter[value]++;
                }
            }
        }

        int successRounds = 0;
        for (int i = 1; i < keysCounter.length; i++) {
            if (keysCounter[i] > 0 && keysCounter[i] <= maxTypingCapacity) {
                successRounds++;
            }
        }

        System.out.println(successRounds);
    }
}
