package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Perimeters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());
        int[] data = new int[dataLength];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(data);

        int max = 0;
        for (int i = dataLength - 1; i >= 2; i--) {
            if (data[i - 1] + data[i - 2] > data[i]) {
                max = Math.max(max, data[i] + data[i - 1] + data[i - 2]);
            }
        }
        System.out.println(max);
    }
}
