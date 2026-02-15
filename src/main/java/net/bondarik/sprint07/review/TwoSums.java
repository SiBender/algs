// https://contest.yandex.ru/contest/25597/run-report/157012601/


/**
 -- ПРИНЦИП РАБОТЫ --
 Проверяем, что общая сумма (S) четная.

 Половина суммы, это целевое значение.  (T = S/2)
 Теперь необходимо ответить на вопрос
 "Можно ли из суммы подмножества чисел получить целевое значение?"

 Теперь можно взять любое число N[i]. (Возьмем максимальное, предварительно упорядочим массив)
 Оно точно должно принадлежать одной сумме.
 Теперь нужно найти числа из которых составится T - N[i]

 Привели задачу к рюкзаку вместимостью T - N[i]

 А это уже условие задачи Золото Лепреконов

 Решается по алгоритму из урока 7/14

 Перейдем к терминологии из задачи о рюкзаке.
 Целевая сумма - вместимость рюкзака
 Числа в массиве - веса.

 Задачу решаем для вместимостей од 0 до целевой
 При этом считаем в подзадачах, что можем использовать веса од первого до текущего

 Матрица dp[i][j]
 i - решение из чисел с нулевого N[0] до N[i]-го включительно
 j - лучшее решение для рюкзака вместимостью j

 На шаге i
 Мы берем решение из шага i - 1
 Для каждого значения суммы для текущей вместимости [i-1][j]
 Мы сравниваем что выгоднее текущее число либо предыдущее решение для вместимости уменьшенной на N[i] = dp[i - 1][j - N[i]] плюс текущее число

 Если dp[i - 1][j - N[i]] + N[i] <= j (поместилось в рюкзак),
 то сравниваем и выбираем максимум между dp[i - 1][j] и dp[i - 1][j - N[i]] + N[i]

 Если dp[i - 1][j - N[i]] + N[i] > j (превышена вместимость рюкзака),
 то сравниваем что выгоднее, решение с прошлого шага либо текущее число,
 т.е MAX(dp[i - 1][j], N[i])

 Таким образом наилучшее решение будет в нижней правой ячейке матрицы dp[][]


 Оптимизация по памяти
 На каждой итерации нам необходима информация только из одного предыдущего ряда матрицы dp[][]
 вместо создания и хранения всей матрицы достаточно хранить 2 массива
 dp[i - 1] и dp[i]
 а после каждой итерации присваивать dp[i - 1] = dp[i]

 Тогда результат хранится в последнем элементе dp[]


 Еще одно замечание про оптимизацию по времени,
 т.к. мы проверяем возможность заполнить рюкзак без остатка,
 достаточно выйти как только впервые появляется сумма равная вместимости

 Суровая действительность (в виде тестов) показала, что это условие не дало выигрыша по времени
 И даже немного замедлило работу за счет дополнительной операции сравнения
 Скорее всего, это связано с тем, что числа упорядочены и решение находится ближе к концу работы алгоритма

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 1) Предыдущий пункт методом прямого доказательства производит корректный перевоз задачи о 2 суммак
 в задачу о рюкзаке
 2) Корректность решения задачи о рюкзаке доказывается по индукции

 Мы заполняем матрицу значениями максимально возмодного наполнения рюкзака емкрсти от 0 до j
 при условии, что можем оперировать только числами от первого до i-го

 Допустим, что матрица корректно заполнена для чисел от первого до (i - 1) - го

 Например, для чисел [1, 1, 2, 3] и максимальной вместимости 5
 |   | 0 | 1 | 2 | 3 | 4 | 5 |
 | 1 | 0 | 1 | 1 | 1 | 1 | 1 |
 | 1 | 0 | 1 | 2 | 2 | 2 | 2 |
 | 2 | 0 | 1 | 2 | 3 | 4 | 4 |
 | 3 | 0 |   |   |   |   |   |

 Тогда для числа 3
 мы для каждого j умеьшаем емкость
 если есть предыдущее значение dp[i-1][j - 3] лучшим результатом может быть либо результат прошлой итерации dp[i - 1][j]
 либо dp[i-1][j - 3] + 3

 Корректность алгоритма основывается на последовательном подсчете
 При этом на каждом шаге выбирается именно лучшее из допустимых значений

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 Целевая сумма S
 Количество чисел N

 В процессе заполнения dp[][] каждый элемент вычисляется строго один раз.

 Размерность матрицы S х N
 Следовательно, временная сложность алгоритма O(S * N)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 В текущей реализации
 память выделяется для массива чисел O(N)
 и на массив результатов заполнения для рюкзака O(S)

 Итого пространственная сложность
 O(2N) + O(2S) = O(N) + O(S) = O(N + S)
 */

package net.bondarik.sprint07.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSums {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());
        int[] data = new int[dataLength];

        int totalSum = 0;

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < dataLength; i++){
            data[i] = Integer.parseInt(tokenizer.nextToken());
            totalSum += data[i];
        }

        if (totalSum % 2 == 1) {
            System.out.println("False");
            return;
        }

        Arrays.sort(data);

        int targetSum = totalSum / 2 - data[data.length - 1];

        if (targetSum < 0) {
            System.out.println("False");
            return;
        }


        int[] newData = new int[data.length - 1];
        System.arraycopy(data, 0, newData, 0, newData.length);


        System.out.println(getMaxFitValue(newData, targetSum) == targetSum ? "True" : "False");

    }

    private static int getMaxFitValue(int[] data, int totalCapacity) {
        int[] dp = new int[totalCapacity + 1];

        for (int line = 0; line < data.length; line++) {
            int currentValue = data[line];
            if (currentValue > totalCapacity) {
                break;
            }

            int[] currentLineResult = new int[totalCapacity + 1];

            for (int capacity = 0; capacity < totalCapacity + 1; capacity++) {
                if (line == 0) {
                    // Заполняем для первой итерации. Сравнения и улучшения не нужны
                    currentLineResult[capacity] = capacity < currentValue ? 0 : currentValue;
                } else {
                    int prevBestValueIndex = capacity - currentValue;
                    if (prevBestValueIndex < 0) {
                        currentLineResult[capacity] = dp[capacity];
                    } else {
                        int newValue = dp[prevBestValueIndex] + currentValue;
                        if (newValue <= totalCapacity) {
                            currentLineResult[capacity] = Math.max(dp[capacity], newValue);
                        } else {
                            currentLineResult[capacity] = Math.max(dp[capacity], currentValue);
                        }
                    }

                }
            }

            dp = currentLineResult;
        }

        return dp[totalCapacity];
    }
}
