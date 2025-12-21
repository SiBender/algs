// https://contest.yandex.ru/contest/24414/run-report/154105619/

/**
 -- ПРИНЦИП РАБОТЫ --
 Под капотом реализуемой хеш-таблицы массив, представляющий фиксированное количество бакетов

 Номер бакета (как и хеш-функция) считается как простой остаток от деления на количество бакетов

 Коллизии разрешаются методом цепочек.

 Каждая пара ключ - значение оборачивается в элемент двусвязного списка
 В бакете хранится ссылка на "голову списка"
 Добавление нового элемента происходит в начало вписка

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

 1) Детерминизм. Нужно гарантировать, что элемент с одним и тем же ключом будет отправлен в один бакет.
 А так же при поиске по ключу мы будем обращаться к тому же бакету.

 Для определения номера бакета используется простой остаток от деления. Это гарантирует детерминизм.

 2) Коллизии ожидаемо возникают. Но та как мы используем связанный список для таких случаев,
 данные с одинаковым номером бакета (одинаковым значением хеша) не будут потеряны при сохранении.

 А при поиске элемента сначала вычисляется номер бакета, затем идет сравнение полей value,
 которые для каждого элемента связного списка строго уникальны.

 3) при удалении проверяется целостность двусвязного списка в бакете.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 Вычисление номера бакета выполняется за константное время O(1)
 Вставка:
    - вычисление номера бакета O(1)
    - вставка в начало списка O(1) или обновление значения в списке O(n) в худшем случае
 Поиск по ключу:
    - вычисление номера бакета O(1)
    - поиск в связном списке от O(1) до O(n) в худшем случае
 Удаление:
    - вычисление номера бакета O(1)
    - поиск в связном списке от O(1) до O(n) в худшем случае
    - удаление найденного элемента из списка O(1)

 В худшем случае временная сложность O(n)
 где n количество добавленных в таблицу элементов.
 Это возможно если подобрать ключи так, что они будут попадать в один бакет.

 Но при подборе достаточно большого (и простого) числа бакетов,
 а так же при равномерном разбросе значений ключей средняя скорость всех операций стремится к O(1)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 Память выделяется на массив бакетов
 А так же на каждый добавленный элемент (ключ - значение)

 Размер массива бакетов фиксированный и относительно небольшой. Его можно принять за константу O(1)

 Память на элементы связных списков линейно зависит только от количества таких элементов. O(n)

 Итого пространственная сложность O(n)

 */



package net.bondarik.sprint04.review.hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class HandMadeHashMap {
    private static final MyHashMap hashMap = new MyHashMap();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandsCount = Integer.parseInt(reader.readLine());

        Integer value;
        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < commandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");

            switch (commandData[0]) {
                case "put":
                    hashMap.put(Integer.parseInt(commandData[1]), Integer.parseInt(commandData[2]));
                    break;
                case "get":
                    value = hashMap.get(Integer.parseInt(commandData[1]));
                    result.add(value == null ? "None" : value.toString());
                    break;
                case "delete":
                    value = hashMap.delete(Integer.parseInt(commandData[1]));
                    result.add(value == null ? "None" : value.toString());
                    break;
            }
        }

        System.out.println(result);
    }
}

class MyHashMap {
    private static final int BUCKETS_COUNT = 17;

    private int size = 0;
    private final Node[] buckets = new Node[BUCKETS_COUNT];

    public void put(int key, int value) {
        int bucketNumber = getBucketNumber(key);

        Node nodeInBucket = buckets[bucketNumber];
        if (nodeInBucket == null) {
            Node newNode = new Node(key, value);
            buckets[bucketNumber] = newNode;
            size++;
        } else {
            boolean isNodeExists = false;
            Node current = nodeInBucket;

            while (current != null) {
                if (current.getKey() == key) {
                    isNodeExists = true;
                    current.setValue(value);
                    break;
                }
                current = current.next;
            }

            if (!isNodeExists) {
                Node newNode = new Node(key, value);
                newNode.next = nodeInBucket;
                nodeInBucket.prev = newNode;
                buckets[bucketNumber] = newNode;
                size++;
            }
        }
    }

    public Integer get(int key) {
        if (isEmpty()) {
            return null;
        }

        int bucketNumber = getBucketNumber(key);
        Node current = buckets[bucketNumber];

        while (current != null) {
            if (current.getKey() == key) {
                return current.getValue();
            }
            current = current.next;
        }

        return null;
    }

    public Integer delete(int key) {
        if (isEmpty()) {
            return null;
        }

        int bucketNumber = getBucketNumber(key);

        Node current = buckets[bucketNumber];

        while (current != null) {
            if (current.getKey() == key) {
                size--;

                Node prev = current.prev;
                Node next = current.next;

                if (prev == null) {
                    buckets[bucketNumber] = next;
                } else {
                    prev.next = next;
                    if (next != null) {
                        next.prev = prev;
                    }
                }

                return current.getValue();
            }
            current = current.next;
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getBucketNumber(int key) {
        return (key % BUCKETS_COUNT + BUCKETS_COUNT) % BUCKETS_COUNT;
    }


    private class Node {
        private final int key;
        private int value;

        Node prev;
        Node next;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
