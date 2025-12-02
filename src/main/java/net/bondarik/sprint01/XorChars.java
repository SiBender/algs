package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class XorChars {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstString = reader.readLine();
        String secondString = reader.readLine();

        char xorSum = 0;

        for (int i = 0; i < firstString.length(); i++) {
            xorSum ^= firstString.charAt(i);
        }

        for (int i = 0; i < secondString.length(); i++) {
            xorSum ^= secondString.charAt(i);
        }

        System.out.println(xorSum);
    }
}
