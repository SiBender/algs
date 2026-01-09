package net.bondarik.sprint05.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringJoiner;

public class HeapSort {
    private static ParticipantHeap heap;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        heap = new ParticipantHeap(dataLength);

        for (int i = 0; i < dataLength; i++) {
            heap.put(Participant.fromString(reader.readLine()));
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < dataLength; i++) {
            result.add(heap.getFirst().getName());
        }

        System.out.println(result);
    }

}

class Participant {
    private final String name;
    private final int points;
    private final int penalty;

    public Participant(String name, int points, int penalty) {
        this.name = name;
        this.points = points;
        this.penalty = penalty;
    }

    public static Participant fromString(String input) {
        String[] fields = input.split(" ");
        return new Participant(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getPenalty() {
        return penalty;
    }
}

class ParticipantHeap {
    private final Comparator<Participant> comparator;
    private final Participant[] data;
    private final int size;
    private int lastElementIndex;

    public ParticipantHeap(int size) {
        this.size = size;
        this.lastElementIndex = 0;
        this.data = new Participant[size + 1];

        this.comparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant o1, Participant o2) {
                //если левый БОЛЬШЕ чем правый, то его поднимаем наверх

                //у кого больше - return 1
                int result = Integer.compare(o1.getPoints(), o2.getPoints());

                if (result != 0) {
                    return result;
                }
                //у кого меньше - return 1
                result = Integer.compare(o2.getPenalty(), o1.getPenalty());
                if (result != 0) {
                    return result;
                }

                //у кого меньше - return 1
                return  o2.getName().compareTo(o1.getName());
            }
        };
    }

    public void put(Participant item) {
        lastElementIndex++;
        data[lastElementIndex] = item;
        siftUp(lastElementIndex);
    }

    public Participant getFirst() {
        Participant first = data[1];
        data[1] = data[lastElementIndex];
        lastElementIndex--;
        siftDown(1);
        return first;
    }

    private void siftUp(int index) {
        int parentIndex = index / 2;
        if (parentIndex >= 1) {
            int compareResult = comparator.compare(data[index], data[parentIndex]);
            if (compareResult > 0) {
                swap(index, parentIndex);
                siftUp(parentIndex);
            }
        }
    }

    private void siftDown(int currentIndex) {
        Participant value = data[currentIndex];
        int leftChildIndex = currentIndex * 2;
        int rightChildIndex = currentIndex * 2 + 1;

        if (leftChildIndex <= lastElementIndex && rightChildIndex <= lastElementIndex) {
            int compareWithLeft = comparator.compare(value, data[leftChildIndex]);
            int compareWithRight = comparator.compare(value, data[rightChildIndex]);

            if (compareWithLeft > 0  && compareWithRight > 0) {
                return;
            } else {
                int leftAndRightCompare = comparator.compare(data[leftChildIndex], data[rightChildIndex]);
                if (leftAndRightCompare < 0) {
                    swap(rightChildIndex, currentIndex);
                    siftDown(rightChildIndex);
                } else {
                    swap(leftChildIndex, currentIndex);
                    siftDown(leftChildIndex);
                }
            }
        } else {
            if (leftChildIndex <= lastElementIndex && comparator.compare(value, data[leftChildIndex]) < 0) {
                swap(leftChildIndex, currentIndex);
                siftDown(leftChildIndex);
            } else if (rightChildIndex <= lastElementIndex && comparator.compare(value, data[rightChildIndex]) < 0) {
                swap(rightChildIndex, currentIndex);
                siftDown(rightChildIndex);
            } else {
                return;
            }
        }
    }

    private void swap(int left, int right) {
        Participant temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
