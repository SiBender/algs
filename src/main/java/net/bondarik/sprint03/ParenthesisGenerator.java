package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringJoiner;

public class ParenthesisGenerator {
    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';

    private static final StringJoiner result = new StringJoiner(System.lineSeparator());

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputNumber = Integer.parseInt(reader.readLine());

        generateLikeBinNum(inputNumber * 2, "");

        System.out.println(result);
    }

    private static void generateLikeBinNum(int n, String prefix) {
        if (n == 0) {
            if (isCorrectParenthesisSequence(prefix)) {
                result.add(prefix);
            }
        } else {
            generateLikeBinNum(n - 1, prefix + LEFT_PARENTHESIS);
            generateLikeBinNum(n - 1, prefix + RIGHT_PARENTHESIS);
        }
    }

    private static boolean isCorrectParenthesisSequence(String sequence) {
        Stack<Character> stack = new Stack<>();
        for (char current : sequence.toCharArray()) {
            if (current == LEFT_PARENTHESIS) {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
