package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class CountingSort {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        int[] counter = new int[3];

        String input = reader.readLine();
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            switch (current) {
                case '0': counter[0]++;break;
                case '1': counter[1]++;break;
                case '2': counter[2]++;break;
            }
        }

        StringJoiner result = new StringJoiner(" ");
        for (int i = 0; i < counter.length; i++) {
            String value = String.valueOf(i);
            while (counter[i] > 0) {
                result.add(value);
                counter[i]--;
            }
        }

        System.out.println(result);
    }
}
