package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class TwoSumGame {
    private static final Set<Integer> existingNums = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int targetSum = Integer.parseInt(reader.readLine());

        while (tokenizer.hasMoreTokens()) {
            int currentNum = Integer.parseInt(tokenizer.nextToken());
            if (existingNums.contains(targetSum - currentNum)) {
                System.out.println(targetSum - currentNum + " " + currentNum);
                return;
            }
            existingNums.add(currentNum);
        }

        System.out.println("None");
    }
}
