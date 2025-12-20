// http://contest.yandex.ru/contest/24414/run-report/154103532/

/**
 -- ПРИНЦИП РАБОТЫ --
 Для всех полученных документов строим единый индекс в виде HashMap

 В качестве ключа hash слова из документа
 В качестве значения пара <номер документа, количество слов с таким хешем>
 
 Далее при обработке запроса поток слов так же делится на слова
 Каждое слово превращается в хеш
 По хешу находятся все документы и количество вхождений слова. 
 
 Результат накапливается в HashMap где:
    ключ - номер документа
    значение - количество вхождений слова из запроса в данном документе
 
 После обработки всех слов из запроса извлекается список ключей (номеров документов), 
 упорядочивается ПО УБЫВАНИЮ количества совпадений и возвращаются первые 5 элементов.
 
 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 ИЗ СООБРАЖЕНИЙ СОВЕСТИ, необходимо проговорить, 
 что корректность алгоритма напрямую зависит от коллизий хеш-функции
 Если коллизии возникают часто, то 2 различных слова воспринимаются как одно с бОльшей частотой повторений
 
 Финальный этап отладки заключался в подборе достаточно больших взаимно простых чисел для метода хеширования


 Итак, допустим, что мы увернулись от коллизий.
 Тогда для каждого уникального слова хеши уникальны.

 Корректность алгоритма основана на свойстве детерминизма хеш-функции
 т.е. для одинаковых слов хеши совпадают.

 Вместо поиска по всем документам, достаточно вычислить жеш искомого слова и по готовому индексу
 определить в каких документах и сколько раз такой хеш встречался.

 Детали по задаче
 1) нумерация документов идет не с 0, а с 1, поэтому номер документа увеличивается на 1 при добавлении в индекс
 2) порядок сортировки результата.
 на примере 3 документов с релевантностью 500, 500 и 1000
 1 - 500
 2 - 500
 3 - 1000

 результат должен быть 3, 1, 2
 Обратный порядок в контексте компаратора означает, что при сравнении
 value1 и value2 нудно инвертировать результат.

 Т.е. для нашего примера
 3 < 1 < 2
 и
 1000 < 500 < 500
 Значит в компараторе инвертируется сравнение по релевантности,
 а если они равны, то сравнивает номера документов без инверсии, чтобы они шли по возрастанию.


  Корректность алгоритма обеспечивается корректной реализацией каждого из перечисленных выше аспектов.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 n - количество докуметнов. N - количество слов во всех документах
 m - количество запросов. M - количество слов во всех запросах

 Весь алгоритм состоит из
 1) вычисления хеш-функции для всех слов из документов и запросов | Общие затраты O(N + M)
 2) наполнения поискового индекса | вставка одного значениея O(1) | Общие затраты O(N)
 3) поиск в индексе по хешам из слов запросов | поиск по индексу O(1) | Общие затраты O(M)
 4) сбор результатов запроса - некоторое чиcло константных операций близкое к O(M)

 Итого
 O(N + M) + O(N) + O(M) + O(M) = O(2N + 3M) = O(N + M)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

 Дополнительная память выделяется только для поискового индекса.
 В худшем случае его размер равен количеству слов во всех документах (если все слова окажутся уникальными)
 Значит, пространственная сложность линейно зависит от N (количество слов в документах)

 O(N)

 */


package net.bondarik.sprint04.review.searchindex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SearchIndex {
    private static final int RESULT_DOCS_COUNT_LIMIT = 5;
    public static final long HASH_BASE = 7L * 109 * 173;
    public static final long HASH_MODULE = 101L * 467 * 719 * 883;

    public static final StringHashSumCalculator HASH_CALC =
            new StringHashSumCalculator(HASH_BASE, HASH_MODULE);

    private static Map<Long, Map<Integer, Integer>> SEARCH_INDEX = new HashMap<>();

    private static final Map<Integer, Integer> EMPTY_MAP = Map.of();

    private static final Map<Long, String> collisionChecker = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int docsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < docsCount; i++) {
            Map<Long, Integer> docSearchIndex = buildSearchIndex(reader.readLine());
            putIntoSearchIndex(i + 1, docSearchIndex);
        }

        int searchQueryCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < searchQueryCount; i++) {
            List<Integer> relevantDocs = getRelevantDocs(reader.readLine());
            joiner.add(
                    relevantDocs.stream()
                                .limit(RESULT_DOCS_COUNT_LIMIT)
                                .map(String::valueOf)
                                .collect(Collectors.joining(" "))
            );
        }

        System.out.println(joiner);
    }

    private static Map<Long, Integer> buildSearchIndex(String document) {
        StringTokenizer tokenizer = new StringTokenizer(document);
        Map<Long, Integer> searchIndex = new HashMap<>();

        while (tokenizer.hasMoreTokens()) {
            searchIndex.merge(HASH_CALC.getHash(tokenizer.nextToken()), 1, Integer::sum);
        }

        return searchIndex;
    }

    private static void putIntoSearchIndex(int docNum, Map<Long, Integer> docIndex) {
        for (Map.Entry<Long, Integer> entry : docIndex.entrySet()) {
            if (SEARCH_INDEX.containsKey(entry.getKey())) {
                SEARCH_INDEX.get(entry.getKey()).put(docNum, entry.getValue());
            } else {
                Map<Integer, Integer> newEntry = new HashMap<>();
                newEntry.put(docNum, entry.getValue());
                SEARCH_INDEX.put(entry.getKey(), newEntry);
            }
        }
    }

    private static List<Integer> getRelevantDocs(String searchQuery) {
        StringTokenizer tokenizer = new StringTokenizer(searchQuery);
        Map<Integer, Integer> docIdByQueryMatch = new HashMap<>();

        Set<Long> tokenHashes = new HashSet<>();
        while (tokenizer.hasMoreTokens()) {
            tokenHashes.add(HASH_CALC.getHash(tokenizer.nextToken()));
        }

        for (long tokenHash : tokenHashes) {
            Map<Integer, Integer> hitCounterByDocId = SEARCH_INDEX.getOrDefault(tokenHash, EMPTY_MAP);
            hitCounterByDocId.forEach((k, v) -> docIdByQueryMatch.merge(k, v, Integer::sum));
        }

        List<Integer> result = new ArrayList<>(docIdByQueryMatch.keySet());
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int compare = Integer.compare(docIdByQueryMatch.get(o2), docIdByQueryMatch.get(o1));
                return compare != 0 ? compare : Integer.compare(o1, o2);
            }
        });

        return result;
    }


}

class StringHashSumCalculator {
    private final long base;
    private final long module;


    StringHashSumCalculator(long base, long module) {
        this.base = base;
        this.module = module;
    }

    public long getHash(String input) {
        long hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash = (hash * base + input.charAt(i)) % module;
        }

        return hash;
    }
}
