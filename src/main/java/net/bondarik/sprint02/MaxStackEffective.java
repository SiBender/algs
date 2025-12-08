package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaxStackEffective {

    private static List<Integer> stackData = new ArrayList<>();
    private static Stack<Integer> leftMaxValues = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputCommandsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < inputCommandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            switch (commandData[0]) {
                case "push":
                    push(Integer.parseInt(commandData[1]));
                    break;
                case "pop":
                    pop();
                    break;
                case "top":
                    top();
                    break;
                case "get_max":
                    getMax();
                    break;
            }
        }
    }

    private static void push(int value) {
        if (leftMaxValues.isEmpty()) {
            leftMaxValues.push(value);
        } else if (value >= leftMaxValues.peek()) {
            leftMaxValues.push(value);
        }

        stackData.add(value);
    }

    private static void pop() {
        if (stackData.isEmpty()) {
            System.out.println("error");
        } else {
            int removedValue = stackData.remove(stackData.size() - 1);
            if (leftMaxValues.peek() == removedValue) {
                leftMaxValues.pop();
            }
        }
    }

    private static void top() {
        if (stackData.isEmpty()) {
            System.out.println("error");
        } else {
            System.out.println(stackData.get(stackData.size() - 1));
        }
    }

    private static void getMax() {
        if (stackData.isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println(leftMaxValues.peek() );
        }
    }
}
