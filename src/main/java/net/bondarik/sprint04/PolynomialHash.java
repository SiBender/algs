package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolynomialHash {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long base = Long.parseLong(reader.readLine());
        long mod = Long.parseLong(reader.readLine());
        String input = reader.readLine();

        long result = 0;
        long multiplier = 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            result += input.charAt(i) * multiplier;
            result %= mod;
            multiplier = (multiplier * base) % mod;
        }

        System.out.println(result);
    }
}
