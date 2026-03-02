package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Revert {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        Stack<String> stack = new Stack<>();
        while(tokenizer.hasMoreTokens()) {
            stack.push(tokenizer.nextToken());
        }

        StringJoiner joiner = new StringJoiner(" ");
        while(!stack.isEmpty()) {
            joiner.add(stack.pop());
        }

        System.out.println(joiner);
    }
}
