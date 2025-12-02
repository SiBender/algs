package net.bondarik;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        String text = "asdz0129";
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
        double qwe = 7;
        int asd = 123;

        System.out.println(asd / qwe);

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
