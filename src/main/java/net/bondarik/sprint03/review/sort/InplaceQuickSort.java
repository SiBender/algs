// https://contest.yandex.ru/contest/23815/run-report/153646171/

/**
 -- ПРИНЦИП РАБОТЫ --
 Создаем отдельные массивы для имен, очков и штрафных очков.
 Информация одного участника хранится в одном и том же индексе каждого из массивов

 Запускаем сортировку по возрастанию.
 В процессе СИНХРОННО пересортируются все 3 массива. Это нужно, чтобы не тратить память на дополнительный массив с первоначальными индексами

 Сортировка происходит по возрастанию, от худших к лучшим.
 Результат собирается проходом по массиву имен в обратном порядке

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 Для сортировки выполняем следующие шаги.
 1) выбор опорного элемента внутри диапазона
 2) операция сравнения. поиск элементов > или < опорного
 3) перестановка элементов местами (swap)
 4) определение границ поддиапазонов для следующей итерации сортировки

 Доказательство корректности алгоритма основано на корректности работы каждой составляющей

 1. Опорный элемент выбирается строго в границах диапазона (диапазон включает края [left, right]).
 Выбирается случайным образом. Это повышает вероятность того, что алгоритм сработает за O(n*log(n))

 2. Сравнение.
 Т.к. мы упорядочиваем по возрастанию, то левее должен оказаться участник у которого
 1) МЕНЬШЕ очков
 2) БОЛЬШЕ штрафных очков
 3) Имя в лексикографическом порядке. Если 2 имени AAA и FFF, то первым должен идти AAA, при этом AAA < FFF.
 т.о. имя должно быть БОЛЬШЕ

 Для этого в методе compare() инвертируется результат сравнения штрафных очков и имен.

 3. Перестановка
 метод swap() использует "третий стул" и по очереди меняет местами элементы во всех 3 массивах

 4. Определение границ
 Алгоритм перебора ВСЕГДА начинает анализ диапазона с крайнего ЛЕВОГО элемента.
 При завершении перебора диапазона возможны 2 ситуации.
 1) Была хотя бы одна перестановка,
 тогда на последней итерации цикла левый указатель сместится ЗА последний элемент <= барьерного элемента (leftPoint = rightPoint)
 2) Перестановок не было (барьерный элемент оказался максимальным в диапазоне) -> левый указатель смотрит на последний элемент <= барьерного элемента

 Это важно учитывать для корректного разбиения текущего диапазона на "левый" и "правый".


 Так как каждый шаг по отдельности работает корректно, то и весь алгоритм корректен. (Делай хорошо и хорошо будет)

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 Все промежуточные этапы работают за константное время и не вносят вклада во временную сложность.

 Так как сортировка перебирает все элементы диапазона, затем разбивает диапазон и повторно проверяет каждый элемент,
 то в худшем случае потребуется n^2 таких проверок.  O(n^2)

 Но в среднем временная сложность O(n*log(n))

 Это можно доказать только на большом числе экспериментов
 На неупорядоченных массивах и возможных вариациях в подборе опорного значения.

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 Дополнительная память выделяется только для нескольких переменных.
 Память линейно зависит от количества участников
 O(n)

 Если бы не было необходимости самостоятельно создавать и хранить сортируемые данные, то
 пространственная сложность была бы O(1)
 */


package net.bondarik.sprint03.review.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class InplaceQuickSort {
    private static final Random RANDOM = new Random();

    private static String[] names;
    private static int[] points;
    private static int[] penaltyPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        names = new String[dataLength];
        points = new int[dataLength];
        penaltyPoints = new int[dataLength];

        for (int i = 0; i < dataLength; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            names[i] = tokenizer.nextToken();
            points[i] = Integer.parseInt(tokenizer.nextToken());
            penaltyPoints[i] = Integer.parseInt(tokenizer.nextToken());
        }

        sort(0, dataLength - 1);

        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = names.length - 1; i >= 0; i--) {
            result.add(names[i]);
        }

        System.out.println(result);
    }

    private static void sort(int left, int right) {
        int leftPointer = left;
        int rightPointer = right;
        int baseValuePoint;

        if (right - left <= 1) {
            int compareResult = compare(left, right);
            if (compareResult > 0) {
                swap(left, right);
            }
            return;
        } else {
            baseValuePoint = RANDOM.nextInt(right - left) + left;

            while (leftPointer < rightPointer) {
                int leftCompareResult = compare(leftPointer, baseValuePoint);
                if (leftCompareResult <= 0) {
                    leftPointer++;
                } else {
                    int rightCompareResult = 1;
                    while (rightPointer > leftPointer && rightCompareResult > 0) {
                        rightCompareResult = compare(rightPointer, baseValuePoint);
                        if (rightCompareResult > 0) {
                            rightPointer--;
                        }
                    }

                    if (rightPointer == baseValuePoint) {
                        baseValuePoint = leftPointer;
                    }
                    swap(leftPointer, rightPointer);
                }
            }
        }

        if (compare(leftPointer, baseValuePoint) > 0) {
            leftPointer--;
        }

        sort(left, leftPointer);

        if (leftPointer < right) {
            sort(leftPointer + 1, right);
        }

    }

    private static int compare(int left, int right) {
        if (left == right) {
            return 0;
        }
        int comparePoints = Integer.compare(points[left], points[right]);
        if (comparePoints != 0) {
            return comparePoints;
        }

        int comparePenaltyPoints = Integer.compare(penaltyPoints[left], penaltyPoints[right]);
        if (comparePenaltyPoints != 0) {
            return comparePenaltyPoints * -1;
        }

        return names[left].compareTo(names[right]) * -1;
    }

    private static void swap(int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        String tmp = names[index1];
        names[index1] =  names[index2];
        names[index2] = tmp;

        int tmpInt = points[index1];
        points[index1] = points[index2];
        points[index2] = tmpInt;

        tmpInt = penaltyPoints[index1];
        penaltyPoints[index1] = penaltyPoints[index2];
        penaltyPoints[index2] = tmpInt;
    }
}
