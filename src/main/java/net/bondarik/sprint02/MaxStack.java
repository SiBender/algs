package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxStack {
    private static List<Integer> stackData = new ArrayList<>();

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
                case "get_max":
                    getMax();
                    break;
            }
        }
    }

    private static void push(int value) {
        stackData.add(value);
    }

    private static void pop() {
        if (stackData.isEmpty()) {
            System.out.println("error");
        } else {
            stackData.remove(stackData.size() - 1);
        }
    }

    private static void getMax() {
        if (stackData.isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println(stackData.stream().max(Comparator.naturalOrder()).get());
        }
    }
}
