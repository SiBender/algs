package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FromDecimalToBin {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(reader.readLine());

        List<String> result = new ArrayList<>();

        int reminder = number;
        do {
            int currentBinaryDigit = reminder % 2;
            result.add(String.valueOf(currentBinaryDigit));
            reminder /= 2;
        }  while (reminder > 0);


            StringBuilder stringBuilder = new StringBuilder();
        for (int i = result.size() - 1; i >= 0; i--) {
            stringBuilder.append(result.get(i));
        }

        System.out.println(stringBuilder.toString());
    }
}
