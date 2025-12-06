package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciRecursive {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int stepsCounter = Integer.parseInt(reader.readLine());

        System.out.println(fibonacci(stepsCounter));
    }

    private static int fibonacci(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }

        return fibonacci(num - 1) + fibonacci(num - 2);
    }
}
