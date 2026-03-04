package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class PrefixTreeCamelCase {
    private static final int MIN_CHAR = 'A';
    private static final int MAX_CHAR = 'Z';


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dictionarySize = Integer.parseInt(reader.readLine());

        Node root = new Node('#');

        for (int i = 0; i < dictionarySize; i++) {
            put(root, reader.readLine());
        }

        int requestsNum =  Integer.parseInt(reader.readLine());

        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < requestsNum; i++) {
            List<String> words = findWords(root, reader.readLine());
            if (words.size() == 0) {
                stringJoiner.add("");
            } else {
                Collections.sort(words);
                words.forEach(stringJoiner::add);
            }
        }

        System.out.println(stringJoiner);
    }

    private static void put(Node root, String s) {
        List<Character> list = extractUpperCaseChars(s);
        putIntoTree(root, s, list, 0);
    }

    private static void putIntoTree(Node root, String s, List<Character> list, int current) {
        root.allWordsInPath.add(s);
        if (current >= list.size()) {
            return;
        }

        int index = list.get(current) - 'A';
        if (root.children[index] != null) {
            putIntoTree(root.children[index], s, list, current+1);
        } else {
            Node child = new Node(list.get(current));
            root.children[index] = child;
            putIntoTree(child, s, list, current+1);
        }
    }

    private static List<Character> extractUpperCaseChars(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            if (c >= MIN_CHAR && c <= MAX_CHAR) {
                list.add(c);
            }
        }

        return list;
    }


    private static List<String> findWords(Node root, String s) {
        for (char c : s.toCharArray()) {
            int index = c - 'A';
            if (root.children[index] == null) {
                return new ArrayList<>();
            } else {
                root = root.children[index];
            }
        }

        return root.allWordsInPath;
    }
}

class Node{
    char value;
    Node[] children;
    List<String> allWordsInPath;

    public Node(char value){
        this.value = value;
        this.children = new Node['Z' - 'A' + 1];
        this.allWordsInPath = new ArrayList<>();
    }
}
