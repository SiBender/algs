package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestWord {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String textLengthLiteral = reader.readLine();
        String text = reader.readLine();


        String longestWord = "";
        int longestWordLen = 0;
        int leftPointer = 0;
        int rightPointer = 0;
        while (rightPointer < text.length()) {
            if (text.charAt(rightPointer) == ' ') {
                int currentWordLen = rightPointer - leftPointer;
                if (currentWordLen > longestWordLen) {
                    longestWordLen = currentWordLen;
                    longestWord = text.substring(leftPointer, rightPointer);
                }
                rightPointer++;
                leftPointer = rightPointer;
            } else {
                rightPointer++;
            }
        }

        if (leftPointer < rightPointer) {
            int currentWordLen = rightPointer - leftPointer;
            if (currentWordLen > longestWordLen) {
                longestWordLen = currentWordLen;
                longestWord = text.substring(leftPointer, rightPointer);
            }
        }

        System.out.println(longestWord);
        System.out.println(longestWordLen);
    }
}
