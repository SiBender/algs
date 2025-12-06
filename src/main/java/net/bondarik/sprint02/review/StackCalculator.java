//https://contest.yandex.ru/contest/22781/run-report/152872419/

package net.bondarik.sprint02.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class StackCalculator {
    private static final Stack<Integer> stack = new Stack<>();

    private static final Map<String, BiFunction<Integer, Integer, Integer>> ARITHMETIC_OPERATIONS =
            Map.of("+", (a, b) -> a + b,
                    "-", (a, b) -> a - b,
                    "*", (a, b) -> a * b,
                    "/", StackCalculator::divide);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());


        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            if (isOperator(token)) {
                execOperation(token);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        System.out.println(stack.pop());
    }

    private static void execOperation(String token) {
        int rightOperand = stack.pop();
        int leftOperand = stack.pop();

        int result = ARITHMETIC_OPERATIONS.get(token).apply(leftOperand, rightOperand);
        stack.push(result);
    }

    private static boolean isOperator(String token) {
        return ARITHMETIC_OPERATIONS.containsKey(token);
    }

    private static int divide(int leftNum, int rightNum) {
        if (leftNum >= 0) {
            return leftNum / rightNum;
        } else {
            return Math.abs(leftNum) % rightNum == 0 ? leftNum / rightNum : leftNum / rightNum - 1;
        }
    }
}
