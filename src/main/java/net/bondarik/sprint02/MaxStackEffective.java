package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MaxStackEffective {

    private static List<Integer> stackData = new ArrayList<>();
    private static TreeSet<Integer> sortedValues = new TreeSet<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            //упорядочиваем по возрастанию
            //второе условие нужно чтобы хранить в сере одинаковые значение с разными индексами
            int compareResult = Integer.compare(stackData.get(o1), stackData.get(o2));
            return compareResult == 0 ? Integer.compare(o1, o2) : compareResult;

        }
    });

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
        stackData.add(value);
        int newIndex = stackData.size() - 1;
        sortedValues.add(newIndex);
    }

    private static void pop() {
        if (stackData.isEmpty()) {
            System.out.println("error");
        } else {
            int positionToRemove = stackData.size() - 1;
            sortedValues.remove(positionToRemove);
            stackData.remove(positionToRemove);
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
            System.out.println(stackData.get(sortedValues.last()));
        }
    }
}
