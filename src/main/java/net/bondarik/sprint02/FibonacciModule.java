package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciModule {
    private static int MODULE;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int stepsCounter = Integer.parseInt(input[0]);
        int modulePower = Integer.parseInt(input[1]);

        MODULE = (int) Math.pow(10, modulePower);

        if (stepsCounter < 2) {
            System.out.println(1);
        } else {
            int fibonacciNum = 0;

            int n0 = 1;
            int n1 = 1;
            for (int i = 2; i <= stepsCounter; i++) {
                fibonacciNum = (n0 + n1) % MODULE;
                n0 = n1;
                n1 = fibonacciNum;
            }

            System.out.println(fibonacciNum);
        }
    }
}
