package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Subsequence {
    private static char[] sequenceToFind;
    private static char[] textData;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        sequenceToFind = reader.readLine().toCharArray();
        textData = reader.readLine().toCharArray();

        System.out.println(isSubsequence(0, 0) ? "True" : "False");
    }

    private static boolean isSubsequence(int sourcePosition, int sequencePosition) {
        if (sequencePosition == sequenceToFind.length) {
            return true;
        } else {
            for (int i = 0; i < textData.length - sourcePosition; i++) {
                if (textData[sourcePosition + i] == sequenceToFind[sequencePosition]) {
                    return isSubsequence(sourcePosition + i + 1, sequencePosition + 1);
                }
            }
            return false;
        }
    }
}
