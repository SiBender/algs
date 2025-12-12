package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cookies {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int childrenCount = Integer.parseInt(reader.readLine());
        int[] childrenCounter = new int[childrenCount];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < childrenCount; i++) {
            childrenCounter[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int cookiesCount = Integer.parseInt(reader.readLine());
        int[] cookiesCounter = new int[cookiesCount];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < cookiesCount; i++) {
            cookiesCounter[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(childrenCounter);
        Arrays.sort(cookiesCounter);

        int happyChildrenCounter = 0;

        int childPointer = 0;
        int cookiePointer = 0;

        while (childPointer < childrenCount && cookiePointer < cookiesCount) {
            if (childrenCounter[childPointer] <= cookiesCounter[cookiePointer]) {
                happyChildrenCounter++;
                childPointer++;
            }
            cookiePointer++;
        }

        System.out.println(happyChildrenCounter);
    }
}
