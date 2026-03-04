package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class PrefixFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        int[] result = new int[input.length()];

        for (int i = 1; i < input.length(); i++) {
            int k = result[i-1];
            while (k > 0 && input.charAt(k) != input.charAt(i)) {
                k = result[k-1];
            }
            if (input.charAt(k) == input.charAt(i)) {
                k++;
            }
            result[i] = k;
        }

        System.out.println(toString(result));
    }

    private static String toString(int[] data) {
        StringJoiner sj = new StringJoiner(" ");
        for (int i : data) {
            sj.add(Integer.toString(i));
        }
        return sj.toString();
    }
}
