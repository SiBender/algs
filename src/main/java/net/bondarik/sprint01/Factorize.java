package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class Factorize {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(reader.readLine());

        StringJoiner stringJoiner = new StringJoiner(" ");

        while (number > 1) {
            for (int i = 2; i <= number; i++) {
                if (number % i == 0) {
                    stringJoiner.add(String.valueOf(i));
                    number /= i;
                    break;
                }
            }
        }

        System.out.println(stringJoiner);
    }
}
