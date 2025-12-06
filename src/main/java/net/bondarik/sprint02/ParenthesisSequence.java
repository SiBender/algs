package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.Stack;

public class ParenthesisSequence {
    private static final Set<Character> openingParenthesis = Set.of('(', '[', '{');

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        Stack<Character> stack = new Stack<>();

        boolean isCorrectSequence = true;

        for (char current : input.toCharArray()) {
            if (openingParenthesis.contains(current)) {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    isCorrectSequence = false;
                    break;
                } else {
                    char fromStack = stack.pop();
                    if (getOpeningParenthesisByClosing(current) != fromStack) {
                        isCorrectSequence = false;
                        break;
                    }
                }
            }
        }

        System.out.println(isCorrectSequence && stack.isEmpty() ? "True" : "False");
    }

    private static char getOpeningParenthesisByClosing(char closingParenthesis) {
        if (closingParenthesis == ')') {
            return '(';
        } else if (closingParenthesis == ']') {
            return '[';
        } else {
            return '{';
        }
    }
}
