//https://contest.yandex.ru/contest/22781/run-report/152868440/

package net.bondarik.sprint02.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class RingBufferDeque {
    private static final String ERROR_MESSAGE = "error";

    private static int head = 0; //first element | front
    private static int tail = 0; //last element  | back
    private static int[] data;
    private static int maxSize;
    private static int currentSize = 0;

    private static StringJoiner result = new StringJoiner(System.lineSeparator());

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputCommandsCount = Integer.parseInt(reader.readLine());
        maxSize = Integer.parseInt(reader.readLine());
        data = new int[maxSize];

        for (int i = 0; i < inputCommandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            switch (commandData[0]) {
                case "push_back":
                    push_back(Integer.parseInt(commandData[1]));
                    break;
                case "push_front":
                    push_front(Integer.parseInt(commandData[1]));
                    break;
                case "pop_front":
                    pop_front();
                    break;
                case "pop_back":
                    pop_back();
                    break;
            }
        }

        System.out.println(result);
    }

    private static void push_back(int val) {
        if (currentSize == maxSize) {
            result.add(ERROR_MESSAGE);
        } else {
            data[tail] = val;
            currentSize++;
            tail = (tail + 1) % maxSize;
        }
    }

    private static void push_front(int val) {
        if (currentSize == maxSize) {
            result.add(ERROR_MESSAGE);
        } else {
            head = head > 0 ? head - 1 : maxSize - 1;
            data[head] = val;
            currentSize++;
        }
    }

    private static void pop_front() {
        if (currentSize > 0) {
            result.add(String.valueOf(data[head]));
            head = (head + 1) % maxSize;
            currentSize--;
        } else {
            result.add(ERROR_MESSAGE);
        }
    }

    private static void pop_back() {
        if (currentSize > 0) {
            int prevElementIndex = tail > 0 ? tail - 1 : maxSize - 1;
            result.add(String.valueOf(data[prevElementIndex]));
            tail = prevElementIndex;
            currentSize--;
        } else {
            result.add(ERROR_MESSAGE);
        }
    }
}
