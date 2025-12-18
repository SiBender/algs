package net.bondarik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int charCode = 0;
        while ((charCode = reader.read()) != '\n') { // '\r' для Windows, '\n' для Unix
            if (charCode != -1) { // Убедимся, что это не конец потока
                System.out.println(charCode);
            }
        }

        System.out.println(System.lineSeparator().length());
    }
}
