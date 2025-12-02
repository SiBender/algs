package net.bondarik.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine().toLowerCase();

        int leftPointer = -2;
        int rightPointer = -1;
        boolean isPalindrome = true;

        while (leftPointer < rightPointer) {
            leftPointer = getNextLeftChar(text, leftPointer);
            rightPointer = getNextRightChar(text, rightPointer);

            if (text.charAt(leftPointer) != text.charAt(rightPointer)) {
                isPalindrome = false;
                break;
            }
        }


        System.out.println(isPalindrome ? "True" : "False");
    }

    private static int getNextLeftChar(String text, int currentPosition) {
        int startPosition = currentPosition < 0 ? 0 : currentPosition + 1;

        for (int i = startPosition; i < text.length(); i++) {
            if (charIsLetter(text.charAt(i))) {
                return i;
            }
        }
        return text.length() - 1;
    }

    private static int getNextRightChar(String text, int currentPosition) {
        int startPosition = currentPosition < 0 ? text.length() - 1 : currentPosition - 1;

        for (int i = startPosition; i >= 0; i--) {
            if (charIsLetter(text.charAt(i))) {
                return i;
            }
        }
        return 0;
    }

    private static boolean charIsLetter(char character) {
        // a -> 97, z -> 122
        int characterValue = (int)(character);
        return (characterValue >= 97 && characterValue <= 122) || (characterValue >= '0' && characterValue <= '9');
    }
}

