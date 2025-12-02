package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstNum = reader.readLine();
        String secondNum = reader.readLine();

        StringBuilder result = new StringBuilder();

        int firstPointer = firstNum.length() - 1;
        int secondPointer = secondNum.length() - 1;

        int firstDigit = 0;
        int secondDigit = 0;
        int extraValue = 0;
        while (firstPointer >= 0 || secondPointer >= 0) {
            if (firstPointer >= 0 && firstNum.charAt(firstPointer) == '1') {
                firstDigit = 1;
            }  else {
                firstDigit = 0;
            }

            if (secondPointer >= 0 && secondNum.charAt(secondPointer) == '1') {
                secondDigit = 1;
            }  else {
                secondDigit = 0;
            }

            int currentSum = firstDigit + secondDigit + extraValue;

            result.append(currentSum % 2);
            extraValue = currentSum / 2;


            firstPointer--;
            secondPointer--;
        }
        if (extraValue == 1) {
            result.append(extraValue);
        }

        System.out.println(result.reverse());
    }
}
