package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyQueueSized {
    private static int head = 0;
    private static int tail = 0;
    private static int[] data;
    private static int maxSize;
    private static int currentSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputCommandsCount = Integer.parseInt(reader.readLine());
        maxSize = Integer.parseInt(reader.readLine());
        data = new int[maxSize];

        for (int i = 0; i < inputCommandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            switch (commandData[0]) {
                case "push":
                    push(Integer.parseInt(commandData[1]));
                    break;
                case "pop":
                    pop();
                    break;
                case "peek":
                    peek();
                    break;
                case "size":
                    size();
                    break;
            }
        }
    }

    private static void push(int val) {
        if (currentSize >= maxSize) {
            System.out.println("error");
        } else {
            data[tail] = val;
            currentSize++;
            tail = (tail + 1) % maxSize;
        }
    }

    private static void pop() {
        if (currentSize > 0) {
            System.out.println(data[head]);
            head = (head + 1) % maxSize;
            currentSize--;
        } else {
            System.out.println("None");
        }
    }

    private static void peek() {
        if (currentSize > 0) {
            System.out.println(data[head]);
        } else {
            System.out.println("None");
        }
    }

    private static void size() {
        System.out.println(currentSize);
    }
}
