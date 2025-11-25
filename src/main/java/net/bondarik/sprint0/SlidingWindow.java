package net.bondarik.sprint0;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SlidingWindow {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int totalLength = Integer.parseInt(reader.readLine().strip());

            List<Integer> dataArray = Arrays.stream(reader.readLine().strip().split(" "))
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());

            int windowSize = Integer.parseInt(reader.readLine().strip());
            double divider = windowSize;

            double[] result = new double[totalLength - windowSize + 1];

            int currentSum = 0;
            int leftPointer = 0;
            int rightPointer = 0;

            while(rightPointer < dataArray.size()) {
                if (rightPointer - leftPointer < windowSize - 1) {
                    currentSum += dataArray.get(rightPointer);
                    rightPointer++;
                } else {
                    currentSum += dataArray.get(rightPointer);
                    result[leftPointer] = currentSum / divider;
                    currentSum -= dataArray.get(leftPointer);

                    leftPointer++;
                    rightPointer++;
                }
            }

            for (double resultValue : result) {
                writer.write(String.valueOf(resultValue));
                writer.write(" ");
            }

            writer.flush();
        }


    }
}
