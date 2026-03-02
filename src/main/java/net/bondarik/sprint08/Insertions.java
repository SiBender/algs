package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Insertions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] data = reader.readLine().toCharArray();

        int linesNum = Integer.parseInt(reader.readLine());

        int additionalLen = 0;
        List<InsertData> list = new ArrayList<>(linesNum);
        for (int i = 0; i < linesNum; i++) {
            String[] split = reader.readLine().split(" ");
            additionalLen += split[0].length();
            list.add(new InsertData(split[0], Integer.parseInt(split[1])));
        }

        Collections.sort(list);

        char[] result = new char[data.length + additionalLen];

        int dataPointer = 0;
        int resultPointer = 0;
        for (InsertData insertData : list) {
            while (dataPointer < insertData.getPosition()) {
                result[resultPointer] = data[dataPointer];
                resultPointer++;
                dataPointer++;
            }
            for (char c : insertData.getValue()) {
                result[resultPointer] = c;
                resultPointer++;
            }
        }

        while (dataPointer < data.length) {
            result[resultPointer] = data[dataPointer];
            resultPointer++;
            dataPointer++;
        }

        System.out.println(new String(result));
    }


}

class InsertData implements Comparable<InsertData> {
    private final char[] value;
    private final int position;

    InsertData(String value, int position) {
        this.value = value.toCharArray();
        this.position = position;
    }

    @Override
    public int compareTo(InsertData o) {
        return Integer.compare(this.position, o.position);
    }

    public char[] getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}