// https://contest.yandex.ru/contest/24810/run-report/154942396/

/**
 -- ПРИНЦИП РАБОТЫ --
 Создаем MaxHeap. Максимальный элемент всегда будет находиться на вершине.
 Поочереди добавляем всех участников в эту структуру.
 Затем так же по одному извлекаем участников и складываем в массив результата.
 За счет того, что при извлечении элемента на вершину автоматически перемещается максимальный из оставшихся
 результат будет упорядочен по убыванию.

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

 Корректность полностью строится на корректном поддержании инварианта структуры куча:
 Родитель больше всех потомков. Максимальный элемент всегда находится на вершине.

 1) Куча реализована поверх массива.
 Так как нумерацию удобнее начинать с 1, размер массива должен быть на 1 больше,
 чем количество добавляемых элементов.
 2) При добавлении нового элемента он перемещается в ячейку сразу за последней занятой.
 После этого производится операция просеивания вверх, т.к. новый элемент может быть больше какого-то из родителей

 3) При извлечении возвращается первый элемент,
 затем на его место переносится последний элемент кучи.
 После этого производится просеивание вниз.

 Т.к. для хранения используется массив фиксированной величины вводится дополнительная переменная,
 которая отслеживает последний добавленный в массив элемент (текущий последний элемент кучи)

 Процесс сравнения реализован через компаратор.
 Мы сравниваем двух участников и ставим выше того кто ближе к первому месту.
 Сравниваются 3 параметра:
    количество очков - прямой порядок (больше = лучше)
    количество штрафных очков - обратный порядок (меньше = лучше)
    алфавитный порядок имен - обратный порядок (меньше = лучше)

 После этого корректность складывается из верного вычисления родительских и дочерних элементов,
 а так же сравнения через компаратор.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 Алгоритм производит N добавлений в кучу, а затем N извлечений из кучи.
 В худшем случае добавление одного элемента выполняется за O(logN)
 Тогда оценка для добавления всех N элементов O(N*logN)

 Хоть извлечение максимального элемента из кучи и выполняется за константу O(1),
 но после этого необходимо дополнительное время на перестроение и поиск нового максимума.
 Это так же занимает O(logN) в худшем случае.
 Тогда оценка извлечение всех N элементов займет O(N*logN)

 Итого O(N*logN) + O(N*logN) = O(N*logN)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

 Память выделяет на массив в котором хранятся участники.
 Его размер равен количеству участников. O(N)

 Так же есть рекурсивные методы просеивания вверх и вниз.
 Их выполнение может занять максимально log(N) шагов.

 Общая пространственная сложность O(N) + O(logN) = O(N)

 */

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
    private static final Comparator<Participant> comparator = new Comparator<Participant>() {
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
    private final Participant[] data;
    private int lastElementIndex;

    public ParticipantHeap(int size) {
        this.lastElementIndex = 0;
        this.data = new Participant[size + 1];
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
