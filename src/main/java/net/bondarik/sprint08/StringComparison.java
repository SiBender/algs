package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringComparison {
    public static final Set<Character> allowedChars = new HashSet<>();

    public static void main(String[] args) throws IOException {
        initCharsSet();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] first = reader.readLine().toCharArray();
        char[] second = reader.readLine().toCharArray();

        List<Character> firstChars = filter(first);
        List<Character> secondChars = filter(second);

        if (firstChars.isEmpty()) {
            System.out.println(secondChars.isEmpty() ? 0 : -1);
            return;
        }

        if (secondChars.isEmpty()) {
            System.out.println(firstChars.isEmpty() ? 0 : 1);
            return;
        }

        int pointer = 0;
        int minSize = Math.min(firstChars.size(), secondChars.size());
        while (pointer < minSize) {
            int delta = firstChars.get(pointer) - secondChars.get(pointer);
            if (delta != 0) {
                System.out.println(delta > 0 ? 1 : -1);
                return;
            }
            pointer++;
        }

        if (firstChars.size() == secondChars.size()) {
            System.out.println(0);
        } else {
            System.out.println(firstChars.size() - secondChars.size() > 0 ? 1 : -1);
        }
    }

    private static List<Character> filter(char[] first) {
        List<Character> result = new ArrayList<>();
        for (char c : first) {
            if (allowedChars.contains(c)) {
                result.add(c);
            }
        }

        return result;
    }

    private static void initCharsSet() {
        for (char c = 'a'; c <= 'z'; c++) {
            if ((c - 'a') % 2 == 1 ) {
                allowedChars.add(c);
            }
        }
    }
}
