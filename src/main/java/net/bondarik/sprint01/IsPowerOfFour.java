package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IsPowerOfFour {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(reader.readLine());

        String binaryString = Integer.toBinaryString(number);

        // Число начинается с 1, затем идет строго четное количество нулей

        boolean isPowerOfFour = isStartsWithOne(binaryString) && isTailLengthEven(binaryString) &&
                                isOnlyZeroesInTail(binaryString);

        System.out.println(isPowerOfFour ? "True" : "False");
    }

    private static boolean isStartsWithOne(String binaryString) {
        return binaryString.charAt(0) == '1';
    }

    private static boolean isTailLengthEven(String binaryString) {
        return (binaryString.length() - 1) % 2 == 0;
    }


    private static boolean isOnlyZeroesInTail(String binaryString) {
        for (int i = 1; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }
}
