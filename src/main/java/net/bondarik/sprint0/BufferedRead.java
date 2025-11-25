package net.bondarik.sprint0;


// Java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BufferedRead {
    public static void main(String[] args) throws IOException {
        List<Integer> arr;

        String[] firstArray;
        String[] secondArray;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String lengthSkippedParameter = reader.readLine().strip();

            firstArray = reader.readLine().strip().split(" ");
            secondArray = reader.readLine().strip().split(" ");
            arr = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < firstArray.length; i++) {
            writer.write(firstArray[i]);
            writer.write(" ");
            writer.write(secondArray[i]);
            writer.write(" ");
        }

        writer.flush();
    }

}
