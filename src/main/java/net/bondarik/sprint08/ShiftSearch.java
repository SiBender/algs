package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ShiftSearch {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLen =  Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int[] data = new int[dataLen];
        for (int i  = 0; i < dataLen; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int patternLen =  Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        int[] pattern = new int[patternLen];
        for (int i  = 0; i < patternLen; i++) {
            pattern[i] = Integer.parseInt(tokenizer.nextToken());
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 0; i <= data.length - pattern.length; i++) {
            if (isPatternFound(data, pattern, i)) {
                stringJoiner.add(String.valueOf(i + 1));
            }
        }

        System.out.println(stringJoiner);
    }

    private static boolean isPatternFound(int[] data, int[] pattern, int start) {
        int delta = data[start] - pattern[0];
        for (int i = 0; i < pattern.length; i++) {
            if (start + i >= data.length) {
                return false;
            }
            if (data[start + i] - pattern[i] != delta) {
                return false;
            }
        }

        return true;
    }
}
