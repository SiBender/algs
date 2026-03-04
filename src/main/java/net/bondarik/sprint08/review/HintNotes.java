package net.bondarik.sprint08.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HintNotes {
    private static final Node PREFIX_TREE_ROOT = new  Node('#');

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();

        int dictionarySize = Integer.parseInt(reader.readLine());
        for (int i = 0; i < dictionarySize; i++) {
            put(PREFIX_TREE_ROOT, reader.readLine());
        }

        System.out.println(checkText(text) ? "YES" : "NO");
    }

    private static boolean checkText(String text) {
        Node parent = PREFIX_TREE_ROOT;
        Node child = null;
        for (int i = 0; i < text.length(); i++) { //все символы кроме последнего
            int childIndex = text.charAt(i) - 'a';

            child = parent.getChildren()[childIndex];
            if (child == null) {
                if (parent.isTerminal()) {
                    child = PREFIX_TREE_ROOT.getChildren()[childIndex];
                    if (child == null) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            parent = child;
        }


        return child != null && child.isTerminal();
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
