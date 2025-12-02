package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WeatherChaos {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());
        int[] data = new int[dataLength];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; ++i) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int chaoticDaysCounter = 0;
        for (int i = 0; i < data.length; i++) {
            if (isChaotic(data, i)) {
                chaoticDaysCounter++;
            }
        }
        System.out.println(chaoticDaysCounter);
    }

    private static boolean isChaotic(int[] dataLine, int index) {
        if (index == 0) {
            if (dataLine.length > 1) {
                return dataLine[index] > dataLine[index + 1];
            } else {
                return true;
            }
        } else if (index == dataLine.length - 1) {
            return dataLine[index] > dataLine[index - 1];
        } else {
            return dataLine[index] > dataLine[index - 1] && dataLine[index] > dataLine[index + 1];
        }
    }
}
