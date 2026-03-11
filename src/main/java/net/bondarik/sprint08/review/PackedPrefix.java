// https://contest.yandex.ru/contest/26133/run-report/158401720/

/**
 -- ПРИНЦИП РАБОТЫ --
 1) Каждая строка распаковывается

 Распаковка реализована через стек.
 Двигаемся посимвольно слева направо
 Мы накапливаем распакованную часть слова
 если есть число, то кладем его на стек,
 и одновременно кладем на стек накопленную распакованную часть.
 Встречаем открывающую скобку
 после этого начинаем накапливать новую текущую строку.
 Как только скобка закрывается,
  - снимаем со стеков множитель и предыдущую распакованную строку
  - текущую накопленную строку умножаем нужное число раз
  к строке из стека конкатенируем справа умноженную строку.

 2) Вычисление общего префикса производится относительно первого введенного слова.
 Посимвольным сравнением определяем общий префикс.
 Как только символ отличается, прерываем сравнение и сохраняем позицию на которой заканчивается общий префикс
 (оно же длина общего префикса)

 При считывании следующего слова сравнение нужно делать не больше,
 чем длина общего префикса вычисленная на предыдущей итерации

 В конце мы имеем длину общего префикса.
 Результат это подстрока заданной длинны от первого считанного (и распакованного) слова

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 Главное на чем строится корректность распаковки - гарантия того, что множители всегда однозначное число (всегда один символ)
 а так же то, что запакованная строка всегда корректное выражение. (корректная скобочная последовательность)
1) Распаковка строки всегда хранит на стеке распакованный вид для уже считанной части строки.
   Стек учитывает любой уровень вложенности.
   И пока внутреннее/вложенное слово не распакуется, не произойдет распаковка внешней части.

 2) Общий префикс, подразумевает, что он есть у всех входных слов.
 Поэтому достаточно все слова поочереди сравнивать с первым.
 А само первое слово с собой имеет общий префикс - всё слово

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
1) Распаковка.
 Каждое слово распаковывается за линейное время.
 Если суммарная длина всех запакованных слов L = l1 + l2 + ... ln

 O(L)

2) Вычисление общего префикса
 Линейно относительно суммарной длины РАСПАКОВАННЫХ строк.
 В лучшем случае если второе слово с первого же символа не совпало с первым, алгоритм завершится
 В худшем все слова окажутся одинаковыми. И придется произвести полный перебор.
 O(S)

 Итого
 S - больше L в разы. Зависит от степени сжатия строки. Это число может быть от единиц до тысяч и больше
 Общая временная сложность
 O(S),
 S - суммарная длина распакованных строк


 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

 1) Распаковка.
 Память выделяется только на 1 распакованную строку.
 Затем она "высвобождается" (когда соблаговолит GC)
 StringBuilder применен, чтобы уменьшить количество выделений памяти под новые строки после конкатенации
 Стеки и StingBuilder'ы занимают гораздо меньше памяти, можно пренебречь (вальсируем)
 O(max(s))

 2) само сравнение не выделяет новой памяти. O(1)

 Итого пространственная сложность
 O(max(s)) - размер самой большой распакованной строки
 */

package net.bondarik.sprint08.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PackedPrefix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int linesCount = Integer.parseInt(reader.readLine());

        String firstWord = unpack(reader.readLine());
        int commonPrefixLength = firstWord.length();

        for (int i = 0; i < linesCount - 1; i++) {
            String next = unpack(reader.readLine());

            commonPrefixLength = findCommonPrefixIndex(firstWord, next, commonPrefixLength);
            if (commonPrefixLength == 0) {
                break;
            }
        }

        System.out.println(firstWord.substring(0, commonPrefixLength));
    }

    private static int findCommonPrefixIndex(String first, String second, int commonPrefixLength) {
        int minLength = Math.min(second.length(), commonPrefixLength);

        for (int i = 0; i < minLength; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                return i;
            }
        }

        return minLength;
    }

    public static String unpack(String s) {
        Stack<Integer> counter = new Stack<>();
        Stack<String> substrings = new Stack<>();

        StringBuilder currentString = new StringBuilder();
        int currentNumber = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                currentNumber = ch - '0';
            } else if (ch == '[') {
                counter.push(currentNumber);
                substrings.push(currentString.toString());

                currentNumber = 0;
                currentString = new StringBuilder();
            } else if (ch == ']') {
                int repeatTimes = counter.pop();
                String previousString = substrings.pop();

                String fixedCurrent = currentString.toString();
                StringBuilder temp = new StringBuilder(previousString);
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(fixedCurrent);
                }

                currentString = temp;
            } else {
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}
