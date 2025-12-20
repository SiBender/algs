package net.bondarik;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> segments = new TreeMap<>();
        segments.put(5, 10);
        segments.put(4, 10);
        segments.put(50, 10);
        segments.put(45, 10);
        segments.put(1, 10);

        Map<Character, Integer> charCount1 = new TreeMap<>();
        Map<Character, Integer> charCount2 = new TreeMap<>();

        charCount1.put('a', 10);
        charCount1.put('b', 20);
        charCount1.put('c', 30);


        charCount2.put('c', 30);
        charCount2.put('b', 20);
        charCount2.put('a', 10);




        for (var entry : segments.entrySet()) {
            System.out.println(entry);
            if (entry.getKey() == 50) {
                segments.remove(50);
            }
        }

        List<Integer> stackData = List.of(9,6,5,4,2,1,1,1,1,1,1);

        TreeSet<Integer> sortedValues = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(stackData.get(o2), stackData.get(o1));
               /* int compareResult = Integer.compare(stackData.get(o2), stackData.get(o1));
                return compareResult == 0 ? Integer.compare(o2, o1) : compareResult;*/
            }
        });

        for (int i = 0; i < stackData.size(); i++) {
            sortedValues.add(i);
        }

        System.out.println(sortedValues.first());
        System.out.println(sortedValues.last());
        System.out.println(sortedValues.pollLast());
        System.out.println(sortedValues.pollFirst());


        String text = "asdz0129";
        String[] qwe = text.split(" ");
        System.out.println(text.charAt(0));
        System.out.println((int)text.charAt(0));
        System.out.println(text.charAt(2));
        System.out.println((int)text.charAt(2));
        System.out.println(text.charAt(3));
        System.out.println((int)text.charAt(3));
        System.out.println(text.charAt(4));
        System.out.println((int)text.charAt(4));
        System.out.println(text.charAt(5));
        System.out.println((int)text.charAt(5));
        System.out.println(text.charAt(7));
        System.out.println((int)text.charAt(7));
        double zxc = 7;
        int asd = 123;

        System.out.println(asd / zxc);

        String[] inputData = {"24", "24"};
        inputData[0].charAt(0);
        long redAmount = Long.parseLong(inputData[0]);
        int blueAmount  = Integer.parseInt(inputData[1]);

        int descriminant = (int) Math.sqrt((redAmount/2 - 2) * (redAmount/2 - 2) - 4 * blueAmount);
        int firstBlueSideSize = ((int)redAmount/2 - 2 + descriminant) / 2;


        int blueWidth = Math.max(firstBlueSideSize, blueAmount / firstBlueSideSize);
        int blueHeight = blueAmount / blueWidth;

        String result = String.format("%d %d", blueWidth + 2, blueHeight + 2);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(result);
        writer.flush();

        long big = 1_000_000_000;
        long big2 = 49998;

        System.out.println();
        System.out.println(big2 * big2);
        System.out.println((int) Math.sqrt((big/2 - 2) * (big/2 - 2)));


    }
}
