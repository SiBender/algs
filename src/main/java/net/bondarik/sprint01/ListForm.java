package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ListForm {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int xLen = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String kAsString = reader.readLine().strip();

        int[] xListForm = new int[xLen];
        for (int i = 0; i < xLen; i++) {
            xListForm[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] kListForm = new int[kAsString.length()];
        for (int i = 0; i < kAsString.length(); i++) {
            kListForm[i] = kAsString.charAt(i) - '0';
        }

        int[] result = new int[Math.max(xListForm.length, kListForm.length)];
        int xPointer = xLen - 1;
        int kPointer = kListForm.length - 1;
        int reminder = 0;
        int currentSum = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            int rightOffset = result.length - 1 - i;
            kPointer = kListForm.length - 1 - rightOffset;
            xPointer = xListForm.length - 1 - rightOffset;

            currentSum = reminder;
            if (kPointer >= 0) {
                currentSum += kListForm[kPointer];
            }
            if (xPointer >= 0) {
                currentSum += xListForm[xPointer];
            }

            result[i] = currentSum % 10;
            reminder = currentSum / 10;
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        if (reminder > 0) {
            stringJoiner.add(String.valueOf(reminder));
        }

        for (int digit : result) {
            stringJoiner.add(String.valueOf(digit));
        }

        System.out.println(stringJoiner);

    }
}
