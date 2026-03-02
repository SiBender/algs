package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PassportCheck {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] name = reader.readLine().toCharArray();

        char[] database = reader.readLine().toCharArray();

        int namePointer = 0;
        int databasePointer = 0;

        if (Math.abs(name.length - database.length) > 1) {
            System.out.println("FAIL");
            return;
        }

        int changesCounter = 0;

        while (namePointer < name.length && databasePointer < database.length
                && changesCounter < 2) {

            if (name[namePointer] == database[databasePointer]) {
                namePointer++;
                databasePointer++;
            } else {
                changesCounter++;
                if (name.length == database.length) {
                    namePointer++;
                    databasePointer++;
                } else {
                    if (name.length > database.length) {
                        namePointer++;
                    } else {
                        databasePointer++;
                    }
                }
            }
        }

        System.out.println(namePointer ==  name.length && databasePointer == database.length ?
                           "OK" : "FAIL");
    }
}
