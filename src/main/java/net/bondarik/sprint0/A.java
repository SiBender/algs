package net.bondarik.sprint0;

import java.util.Scanner;

public class A {

    private static int getSum(int a, int b) {
        return -1; // Ваше решение
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(getSum(a, b));
        scanner.close();
    }
}