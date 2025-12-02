package net.bondarik.sprint01.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ClosestZeroes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int arrayLength = Integer.parseInt(reader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        int[] sourceArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            sourceArray[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        //int[] distances = new int[arrayLength];


        int leftZeroPosition = -1; //еще не найден при поиске с лева на право
        for (int i = 0; i < arrayLength; i++) {
            if (sourceArray[i] == 0) {
                leftZeroPosition = i;
            }

            if (leftZeroPosition == - 1) {
                sourceArray[i] = Integer.MAX_VALUE;
            } else {
                sourceArray[i] = i - leftZeroPosition;
            }
        }

        int rightZeroPosition = -1; //еще не найден при поиске с права на лево
        for (int i = arrayLength - 1; i >= 0; i--) {
            if (sourceArray[i] == 0) {
                rightZeroPosition = i;
            }

            if (rightZeroPosition != - 1) {
                sourceArray[i] = Math.min(sourceArray[i], rightZeroPosition - i);
            }
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int value : sourceArray) {
            stringJoiner.add(String.valueOf(value));
        }
        System.out.println(stringJoiner);
    }
}
