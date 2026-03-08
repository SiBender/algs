// https://contest.yandex.ru/contest/26133/run-report/158217114/

/**
 -- ПРИНЦИП РАБОТЫ --

 Сначала строим префиксное дерево по словарю.
 Затем поток символов из входного текста прогоняем через это дерево, начиная от корня.

 Наивное решение:
     Мы проходим по дереву. Встречаем первый терминальный узел (node.isTerminal == true)
     для следующего символа начинаем поиск от корня префиксного дерева.

     Или наоборот.
     При проходе по дереву от корня к листьям мы продолжаем движение до последнего листа.
     Даже если по пути встречается терминальный узел, мы его игнорируем.
     Поиск от корня начинается только если у текущего узла нет подходящего потомка.

 Оба этих однобоких варианта оказываются нежизнеспособными.

 Для корректировки решения нужно совместить оба варианта.
 Основная стратегия - двигаться до последнего листа, пока не окажется нужного потомка.
 Для того чтобы предусмотреть случай, когда прерваться нужно было в предыдущем терминальном узле
 эти случаи будут сохраняться в отдельную очередь.
 И если поиск по первому методу "ломается", то начинать с символа из очереди.

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Если входная последовательность корректна, то при сравнении слов с префиксным деревом
 возможный только 2 варианта.
 1) мы встретили терминальный символ и начали проверку с корня. (слово действительно заканчивалось на этом символе)
 2) текущий терминальный символ не относится к текущему слову и нужно искать следующий терминальный узел

 Т.к. мы всегда идем по второму пути, в очередь попадают все варианты корректные для первого случая.

 Тогда либо мы добираемся до конца текста,
 либо все возможные варианты исчерпываются, очередь попыток пуста и мы не дошли до конца текста.
 Соответственно

 Краеугольный камень корректности данного алгоритма в том, что если текст корректный, то его первый символ
 ТОЧНО нужно искать от корня префиксного дерева.
 После этого можно утверждать, что все остальные шаги так же будут корректными.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 1) Создание префиксного дерева
  Линейно относительно количества символов во всех словах исходного словаря.
  O(D), где D - сумма длин всех строк словаря

 2) Алгоритм проверки входной последовательности.
 На каждый символ происходит условно одна проверка / переход по префиксному дереву
 В худшем случае алгоритм начнется от 1 символа, затем от 2 и так далее до последнего.
 Тогда, если текст длины N символов, то сложность N * (N - 1) / 2
 O (N ^ 2)

 3) вставка и извлечение из очереди - константа O(1)

 Итог временная сложность
 O(D) + O(N ^ 2) = O(N ^ 2)

 D пренебрежимо мало относительно N ^ 2

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

 Память выделяется на префиксное дерево и на хранение входной строки.

 Размер дерева
 X слов общей длиной D
 В каждом узле хранится массив со ссылками на потомков. Размер ∣Σ∣ - мощность алфавита. В нашем случае 26.
 Количество узлов в дереве (в худшем случае) стремится к D

 O(D * ∣Σ∣) = O(D) //∣Σ∣ - константа, которую можно отбросить.

 Размер текста N

 Размер очереди для повторных попыток O(N) (в худшем случае)

 Итого общая пространственная сложность
 O(D + N + N) = O(D + N)

 */


package net.bondarik.sprint08.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class HintNotes {
    private static final Node PREFIX_TREE_ROOT = new Node('#');
    private static final Deque<Integer> startFromRootTasks = new LinkedList<>();
    private static int lastAddedIndex = 0;

    public static void main(String[] args) throws IOException {
        startFromRootTasks.add(0);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();

        int dictionarySize = Integer.parseInt(reader.readLine());
        for (int i = 0; i < dictionarySize; i++) {
            put(PREFIX_TREE_ROOT, reader.readLine());
        }

        while (!startFromRootTasks.isEmpty()) {
            if (checkText(text, startFromRootTasks.poll())) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

    private static boolean checkText(String text, int start) {
        Node parent = PREFIX_TREE_ROOT;
        Node child = null;
        for (int i = start; i < text.length(); i++) { //все символы кроме последнего
            int childIndex = calcIndex(text.charAt(i));

            child = parent.getChildren()[childIndex];
            if (child == null && parent.isTerminal()) {
                child = PREFIX_TREE_ROOT.getChildren()[childIndex];
            }

            if (child != null) {
                // Предположим, что текущий символ конец слова.
                // Создадим попытку начать из корня со следующего символа
                int startFromRootIndex = i + 1;
                if (child.isTerminal()
                    && startFromRootIndex < text.length()
                    && PREFIX_TREE_ROOT.getChildren()[calcIndex(text.charAt(startFromRootIndex))] != null) {

                    if (startFromRootIndex > lastAddedIndex) {
                        startFromRootTasks.add(startFromRootIndex);
                        lastAddedIndex = startFromRootIndex;
                    }
                }
            } else {
                return false;
            }
            parent = child;
        }


        return child != null && child.isTerminal();
    }

    private static int calcIndex(char ch) {
        return ch - 'a';
    }

    private static void put(Node parent, String word) {
        Node currentChild;

        for (int i = 0; i < word.length(); i++) {
            int childIndex = word.charAt(i) - 'a';

            currentChild = parent.getChildren()[childIndex];
            if (currentChild == null) {
                currentChild = new Node(word.charAt(i));
                parent.getChildren()[childIndex] = currentChild;
            }

            boolean isLastChar = (i == word.length() - 1);
            if (isLastChar) {
                currentChild.setTerminal(true);
            }

            parent = currentChild;
        }
    }
}

class Node {
    private final char value;
    private boolean isTerminal;
    private final Node[] children;

    Node(char value) {
        this.value = value;
        this.isTerminal = false;
        this.children = new Node['z' - 'a' + 1];
    }

    public char getValue() {
        return value;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }
}
