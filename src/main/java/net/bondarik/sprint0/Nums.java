package net.bondarik.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Nums {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        System.out.println(
                (isEven(a) == isEven(b)) && (isEven(b) == isEven(c)) ?
                "WIN" : "FAIL"
        );
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
