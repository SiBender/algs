package net.bondarik.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinkedListQueue {
    private static QueueNode head = null;
    private static QueueNode tail = null;
    private static int currentSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputCommandsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < inputCommandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            switch (commandData[0]) {
                case "put":
                    put(Integer.parseInt(commandData[1]));
                    break;
                case "get":
                    get();
                    break;
                case "size":
                    size();
                    break;
            }
        }
    }

    private static void put(int val) {
        QueueNode newNode = new QueueNode(val);
        if (currentSize == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        currentSize++;
    }

    private static void get() {
        if (currentSize > 0) {
            System.out.println(head.value);
            head = head.next;
            currentSize--;
        } else {
            System.out.println("error");
        }
    }

    private static void size() {
        System.out.println(currentSize);
    }
}

class QueueNode {
    public int value;
    public QueueNode next;

    public QueueNode(int value) {
        this.value = value;
        next = null;
    }
}
