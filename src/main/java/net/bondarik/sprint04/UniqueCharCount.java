package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class UniqueCharCount {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] data = reader.readLine().toCharArray();
        Set<Character> uniqueChars = new HashSet<>();
        int maxLen = 0;
        int leftPointer = 0;
        int rightPointer = 0;
        while (rightPointer < data.length) {
            if (!uniqueChars.add(data[rightPointer])) {
                maxLen = Math.max(maxLen, uniqueChars.size());

                while (uniqueChars.contains(data[rightPointer])) {
                    uniqueChars.remove(data[leftPointer]);
                    leftPointer++;
                }
            } else {
                rightPointer++;
            }
        }
        maxLen = Math.max(maxLen, uniqueChars.size());

        System.out.println(maxLen);
    }
}
