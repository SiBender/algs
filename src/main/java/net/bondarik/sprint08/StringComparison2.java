package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class StringComparison2 {
    public static final Set<Character> allowedChars = new HashSet<>();

    public static void main(String[] args) throws IOException {
        initCharsSet();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] first = reader.readLine().toCharArray();
        char[] second = reader.readLine().toCharArray();

        int firstPointer = 0;
        int secondPointer = 0;
        while (firstPointer < first.length && secondPointer < second.length) {
            if (!allowedChars.contains(first[firstPointer])) {
                firstPointer++;
            } else if (!allowedChars.contains(second[secondPointer])) {
                secondPointer++;
            } else {
                int delta = first[firstPointer] - second[secondPointer];
                if (delta != 0) {
                    System.out.println(delta > 0 ? 1 : -1);
                    return;
                }
                firstPointer++;
                secondPointer++;
            }
        }

        firstPointer = findNextAllowedIndex(firstPointer, first);
        secondPointer = findNextAllowedIndex(secondPointer, second);

        if (firstPointer == first.length && secondPointer == second.length) {
            System.out.println(0);
            return;
        }
        if (firstPointer < first.length) {
            System.out.println(1);
        }
        if (secondPointer < second.length) {
            System.out.println(-1);
        }

    }

    private static int findNextAllowedIndex(int start, char[] array) {
        while (start < array.length) {
            if (allowedChars.contains(array[start])) {
                break;
            }
            start++;
        }

        return start;
    }

    private static void initCharsSet() {
        for (char c = 'a'; c <= 'z'; c++) {
            if ((c - 'a') % 2 == 1) {
                allowedChars.add(c);
            }
        }
    }
}
