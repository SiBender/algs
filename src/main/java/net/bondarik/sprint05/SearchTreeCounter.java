package net.bondarik.sprint05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchTreeCounter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(getSearchTreesCount(Integer.parseInt(reader.readLine())));
    }

    private static int getSearchTreesCount(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }

        int totalCount = 0;
        for (int i = 1; i <= num; i++) {
            totalCount += getSearchTreesCount(i - 1) * getSearchTreesCount(num - i);
        }

        return totalCount;
    }
}
