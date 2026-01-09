package net.bondarik.sprint05.review;

import java.util.Comparator;

public class HeapSort {

    public static void main(String[] args) {

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
                //если левый Больше чем правый, то его поднимаем наверх

                //у кого больше - обратный результат
                int result = Integer.compare(o2.getPoints(), o1.getPoints());

                if (result != 0) {
                    return result;
                }
                //у кого меньше - нормальный результат
                result = Integer.compare(o1.getPenalty(), o2.getPenalty());
                if (result != 0) {
                    return result;
                }

                //у кого меньше - нормальный результат
                return  o1.getName().compareTo(o2.getName());
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
        if (index >= 1) {
            int compareResult = comparator.compare(data[index], data[parentIndex]);
            if (compareResult < 0) {
                swap(index, parentIndex);
                siftUp(parentIndex);
            }
        }
    }

    private void siftDown(int index) {
        Participant value = data[index];
        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        if (leftIndex < lastElementIndex && rightIndex < lastElementIndex) {
            if (value > data[leftIndex] && value > heap[rightIdx]) {
                return idx;
            } else {
                if (heap[leftIdx] < heap[rightIdx]) {
                    swap(heap, rightIdx, idx);
                    return siftDown(heap, rightIdx);
                } else {
                    swap(heap, leftIdx, idx);
                    return siftDown(heap, leftIdx);
                }
            }
        } else {
            if (leftIdx < heap.length && heap[leftIdx] > value) {
                swap(heap, leftIdx, idx);
                return siftDown(heap, leftIdx);
            } else if (rightIdx < heap.length && heap[rightIdx] > value) {
                swap(heap, rightIdx, idx);
                return siftDown(heap, rightIdx);
            } else {
                return idx;
            }
        }
    }

    private void swap(int left, int right) {
        Participant temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
