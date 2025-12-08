//https://contest.yandex.ru/contest/22781/run-report/152872419/


/**
 -- ПРИНЦИП РАБОТЫ --
 В соответствии с описанием алгоритма, в классе есть стек для хранения чисел
 На вход поступает последовательность элементов разделенных пробелами
 Каждый элемент считывается по отдельности
 Если мы получили число, то сохраняем его на вершине стека

 если получили символ арифметической операции,то:
 1)извлекаем с вершины стека 2 элемента,
 2)из таблицы ARITHMETIC_OPERATIONS извлекаем нужную арифметическую операцию в виде BiFunction<Integer, Integer, Integer> (на входе 2 числа, на выходе одно)
 3)выполняем для них нужную арифметическую операцию и результат кладем на вершину стека

 После того как весь входной поток данных последовательно обработан возвращаем число с вершины стека

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
1) Входной поток обрабатывается строго слева направо и обрабатывается по одному элементу за раз
2) Хранение чисел на стеке гарантирует, что арифметическая операция будет выполняться строго с двумя последними числами,
 не зависимо от того это чиста из входного потока или результат предыдущих вычислений
3) Порядок чисел при выполнении арифметической операции.
 Для сложения и умножения порядок не важен.
 Поэтому разберемся на примере вычитания.

 Если на вход пришли "10 5 -"
 необходимо вычислить результат выражения "10 - 5 = ?"
 т.к. для хранения чисел используется стек, порядок меняется на обратный (FILO)
 для пояснения, договоримся, что вершина стека сверху, тогда после добавления 10, а затем 5 имеем следующую картину

 5  <- вершина стека
 10
 ___

 Значит, для корректной работы алгоритма мы
 извлекаем первый раз число с вершины и сохраняем как ВТОРОЙ операнд выражения
 извлекаем еще раз число с вершины стека и сохраняем как ПЕРВЫЙ операнд
 Благодаря этому получаем корректное выражение "10 - 5"

 Аналогично для деления.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В реализации используются:
 арифметические операции - O(1)
 добавление и извлечение со стека - O(1)
 Поиск ключа в HashMap - O(1)
 Извлечение значения по ключу из HashMap - O(1)

 В худшем случае извлечение из HashMap по ключу O(log(n))
 Но в данном случае размер HashMap маленький и никогда не будет увеличиваться.
 Поэтому принимаем временную сложность извлечения за константу O(1)

 Итого
 O(1) + O(1) + O(1) + O(1) = O(1)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

 Память выделяется для таблицы ARITHMETIC_OPERATIONS
 и для стека хранения чисел

 Таблица ARITHMETIC_OPERATIONS всегда будет фиксированного размера. Т.е. O(1)
 Размер стека чисел линейно зависит от количества входных данных
 В худшем случае входной поток будет полностью состоять из чисел. В среднем n/2
 Т.о. для стека пространственная сложность O(n)

 Итого
 O(1) + O(n) = O(n)
 */


package net.bondarik.sprint02.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class StackCalculator {
    private static final Stack<Integer> stack = new Stack<>();

    private static final Map<String, BiFunction<Integer, Integer, Integer>> ARITHMETIC_OPERATIONS =
            Map.of("+", (a, b) -> a + b,
                    "-", (a, b) -> a - b,
                    "*", (a, b) -> a * b,
                    "/", StackCalculator::divide);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());


        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            if (isOperator(token)) {
                execOperation(token);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        System.out.println(stack.pop());
    }

    private static void execOperation(String token) {
        int rightOperand = stack.pop();
        int leftOperand = stack.pop();

        int result = ARITHMETIC_OPERATIONS.get(token).apply(leftOperand, rightOperand);
        stack.push(result);
    }

    private static boolean isOperator(String token) {
        return ARITHMETIC_OPERATIONS.containsKey(token);
    }

    private static int divide(int leftNum, int rightNum) {
        if (leftNum >= 0) {
            return leftNum / rightNum;
        } else {
            return Math.abs(leftNum) % rightNum == 0 ? leftNum / rightNum : leftNum / rightNum - 1;
        }
    }
}
