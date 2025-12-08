//https://contest.yandex.ru/contest/22781/run-report/152868440/

/**
-- ПРИНЦИП РАБОТЫ --
 очередь работает по принципу FIFO.
 Первый добавленный элемент должен извлекаться в первую очередь
 Но так как нам нужно реализовать двустороннюю очередь необходимо предоставить возможность
 добавлять и извлекать значение с обоих концов.

На этапе инициации создается массив фиксированной длины
Два указателя:
    head - указывает на первый элемент очереди, с ним работаем в случае команд push_fron и pop_front
    tail - указывает на следующую свободную ячейку для добавления нового элемента в конец очереди. С ним работает push_back и pop_back

 Дополнительное поле currentSize хранит текущее число элементов в очереди.
 И это поле краеугольный камень корректности работы алгоритма

 В самом начале, а так же в случае когда очередь опустела в процессе работы,
 head и tail указывают на одну и ту же ячейку.

 Т.к. в двусторонней очереди логика симметрична, объясню принцип работы для методов push_back и pop_back

 push_back - добавить элемент в конец очереди
 Проверяем, что есть место для добавления элемента.  currentSize < maxSize
 Если место есть, то помещаем новый элемент в ячейку tail, после этого смещаем tail на соседнюю ячейку справа
 При выходе за пределы массива с элементами очереди tail перемещается на первый элемент.
 currentSize увеличиваем на 1

 pop_back - извлечь элемент из конца очереди
 Проверяем, что очередь не пуста. currentSize > 0
 Теперь нужно получить ссылку на последний элемент. Это будет первая СЛЕВА ячейка от tail.
 Соответственно, если tail = 0 то элемент будет в последней ячейке массива.
 Извлекает его из полученного элемента массива
 уменьшаем currentSize на 1
 смещаем tail на соседний левый элемент (с учетом перехода из начала массива в конец при движении влево)

 Для push_front и pop_front логика зеркальна.
 Только работает с указателем head и смещается в противоположную сторону с тем же правилом перехода через границы массива

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Очередь поддерживает порядок в котором элементы были добавлены
 Реализауия алгоритма построена на последовательном добавлкеии и извлечении элементов.
 Каждый раз указатели смещаются строго на 1
 С одной стороны это гарантирует сохранение порядка
 С другой (от обратного) полностью исключает возможность извлечения элемента из "середины" массива.

 Так же ключевую роль играет контроль количества уже добавленных элементов (currentSize).
 Он защищает от извлечения из пустой очереди, а так же от переполнения - ситуации, когда, например, tail смещался вправо,
 перешел за край массива, оказался левее head, а затем после нескольких вставок "перепрыгнул" через head

 Таким образом гарантируется консистентность данных в структуре

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В алгоритме используются извлечение по индексу, вставка по индексу, а так же арифметические операции
Для массива вставка и извлечение по индексу выполняется за константу O(1)
 Арифметические операции так же выполняются за O(1)

 Т.о. временная сложность всех методов O(1)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Память выделяется для переменных head, teil, maxSize и currentSize
 Для них пространственная сложность константа O(1)

 А так же массив с элементами очереди
 Его величина постоянна и равна максимальной емкости очереди
 то есть O(n)

 Итого O(1) + O(n) = O(n)
 т.к. O(1) пренебрежимо мало в сравнении с (n)

*/


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
