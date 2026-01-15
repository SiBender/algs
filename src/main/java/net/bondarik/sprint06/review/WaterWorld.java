// https://contest.yandex.ru/contest/25070/run-report/155196935/

/**
 -- ПРИНЦИП РАБОТЫ --

 Рассмотрим карту как таблицу связности. Участок суши это вершина графа.

 Сначала выбираем любую из вершин (поиск начинаем попоряд сверху слева).
 Для этой вершины запускаем обход графа в глубину. (Это не критично, можно и в ширину)
 И в процессе обхода считаем сколько вершин попало в компоненту связности.

 После этого ищем следующую не посещенную вершину и повторяем обход.

 После каждого успешного обхода увеличиваем счетчик островов и вычисляем максимальный размер из найденных.

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

 Т.к. нас не интересуют пути в графе или максимальные расстояния, не нужно применять механику двойной перекраски вершин.

 Упрощенный алгоритм DFS (обход в глубину) берет вершину из стека
 Проверяет, что она не посещена.
 Если это верно, то:
    - помечаем вершину посещенной;
    - увеличиваем счетчик размера компоненты связности графа (размер текущего острова);
    - ищем всех не посещенных соседей и помещаем их в стек;

 Если вершина попала в стек но после извлечениия оказалось песещенной,
 значит она уже была обработана на одной из предыдущих итераций и её можно просто отбросить


 Корректность работы всего алгоритма основывается на
 1) корректный поиск не посещенной вершины для запуска алгоритма DFS
 Тут всё просто т.к. мы поочередно перебираем все элементы массивов с первого до последнего
 2) корректный поиск соседей внутри DFS.
 В этом случае нужно проверить, что каждая из 4 прилегающих ячеек массива не попадает в границы массива
 (иначе IndexOutOfBoundsException) и является не посещенным участком суши (== '#')

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 Пусть общее число элементов на карте x * y = N

 Временная сложность складывается из 2 составляющих
 1) Поиск следующего не посещенного элемента - все элементы перебираются ровно 1 раз O(N)
 2) Обход в глубину.
 В худшем случае если у нас большое полностью заполненное островом ('#'),
 то при работе мы извлекаем и отмечаем точку посещенной 1 раз,
 но при поиске соседей одна и та же точка может попасть в стек максимум 4 раза (фактически менбше, но пока пренебрежем)

 Итого хужшая возможная сложность обхода по времени равна 5*O(N) = O(N)


 Общая временная сложность O(N) + O(N) = O(N)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Память выделяется для самой карты O(N)
Для стека при обходе графа. В худшем случае в стек могут попасть все вершины. -> O(N)
 И для нескольких констант O(1)

 Общая пространственная сложность O(N) + O(N) + O(1) = O(N)
 */


package net.bondarik.sprint06.review;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WaterWorld {
    private static final char VISITED = 'X';
    private static final char EARTH = '#';
    private static final char WATER = '.';

    private static char[][] map;

    private static int linesCount;
    private static int lineLength;

    private static int lastCheckedPoint = 0;
    private static int totalElementsCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        linesCount = Integer.parseInt(data[0]);
        lineLength = Integer.parseInt(data[1]);

        totalElementsCount = linesCount * lineLength;
        map = new char[linesCount][lineLength];
        for (int i = 0; i < linesCount; i++) {
            map[i] = reader.readLine().toCharArray();
        }

        int maxSize = 0;
        int islandsCount = 0;

        while (true) {
            int[] point = getNextUnvisited();
            if (point == null) {
                break;
            }

            int islandSize = getSize(point);
            islandsCount++;
            maxSize = Math.max(maxSize, islandSize);
        }

        System.out.println("%d %d".formatted(islandsCount, maxSize));
    }

    private static int getSize(int[] point) {
        Stack<int[]> stack = new Stack<>();

        stack.push(point);
        int size = 0;

        while (!stack.isEmpty()) {
            int[] currentPoint = stack.pop();
            if (map[currentPoint[0]][currentPoint[1]] == EARTH) {
                map[currentPoint[0]][currentPoint[1]] = VISITED;
                size++;

                List<int[]> unvisitedNeighbors = findUnvisitedNeighbours(currentPoint[0], currentPoint[1]);
                unvisitedNeighbors.forEach(stack::push);
            }
        }

        return size;
    }

    private static List<int[]> findUnvisitedNeighbours(int line, int column) {
        List<int[]> result = new ArrayList<>();

        // up
        if (line > 0 && map[line - 1][column] == EARTH) {
            result.add(new int[]{line - 1, column});
        }
        //down
        if (line < linesCount - 1 && map[line + 1][column] == EARTH) {
            result.add(new int[]{line + 1, column});
        }
        //left
        if (column > 0 && map[line][column - 1] == EARTH) {
            result.add(new int[]{line, column - 1});
        }
        //right
        if (column < lineLength - 1 && map[line][column + 1] == EARTH) {
            result.add(new int[]{line, column + 1});
        }

        return result;
    }


    private static int[] getNextUnvisited() {
        int line = 0;
        int column = 0;
        for (int i = lastCheckedPoint; i < totalElementsCount; i++) {
            line = i / lineLength;
            column = i - line * lineLength;

            if (map[line][column] == EARTH) {
                return new int[]{line, column};
            } else {
                lastCheckedPoint++;
            }
        }

        return null;
    }
}
