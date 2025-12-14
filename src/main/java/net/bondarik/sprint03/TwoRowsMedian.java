package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoRowsMedian {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int row1Len = Integer.parseInt(reader.readLine());
        int row2Len = Integer.parseInt(reader.readLine());

        int[] data = new int[row1Len + row2Len];

        StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());

        if (row1Len >= row2Len) {
            addToArray(data, tokenizer2, 0);
            addToArray(data, tokenizer1, row2Len);
        } else {
            addToArray(data, tokenizer1, 0);
            addToArray(data, tokenizer2, row1Len);
        }

        int medianOffset = findLeftLen(data, Math.min(row1Len, row2Len),  data.length / 2, 0, 0, Math.min(row1Len, row2Len));

        System.out.println(getMedian(data, Math.min(row1Len, row2Len), medianOffset));
    }

    private static String getMedian(int[] data, int arraysBorderPoint, int medianOffset) {
        int leftPartTotalLength = data.length / 2;
        if (data.length % 2 == 1) {
            if (medianOffset == 0) {
                return String.valueOf(
                        Math.min(
                                data[medianOffset],
                                data[arraysBorderPoint + leftPartTotalLength]
                        ));
            } else if (medianOffset < arraysBorderPoint) {
                return String.valueOf(
                        Math.min(
                                data[medianOffset],
                                data[arraysBorderPoint + leftPartTotalLength - medianOffset]
                        ));
            } else {
                return String.valueOf(data[leftPartTotalLength]);
            }
        } else {
            int medianValueSum = 0;
            if (medianOffset == 0) {
                int leftMax = data[arraysBorderPoint + leftPartTotalLength - 1];
                int rightMin1 = data[0];
                int rightMin2 = data.length / 2 == arraysBorderPoint ? rightMin1 :
                        data[data.length - leftPartTotalLength + arraysBorderPoint];

                int rightMin = Math.min(rightMin1, rightMin2);
                medianValueSum = leftMax + rightMin;
            } else if (medianOffset < arraysBorderPoint) {
                int leftPoint1 = medianOffset - 1;
                int leftPoint2 = arraysBorderPoint + leftPartTotalLength - medianOffset - 1;
                int leftMax = leftPoint1 >= 0 ? Math.max(data[leftPoint1], data[leftPoint2]) : data[leftPoint2];

                int rightPoint1 = leftPoint1 + 1;
                int rightPoint2 = leftPoint2 + 1;
                int rightMin = Math.min(data[rightPoint1], data[rightPoint2]);

                medianValueSum = leftMax + rightMin;
            } else {
                int leftPoint = leftPartTotalLength - 1;
                int rightPoint = leftPartTotalLength;
                medianValueSum = data[leftPoint] + data[rightPoint];
            }
            if (medianValueSum % 2 == 0) {
                return String.valueOf(medianValueSum / 2);
            } else {
                return medianValueSum / 2 + ".5";
            }
        }
    }

    private static int findLeftLen(int[] data, int arraysBorderPoint, int maxLen, int leftOffset, int leftBorder, int rightBorder) {
        //System.out.println(leftBorder + " - " + leftOffset + " - " + rightBorder);
        int[] leftPartPoints = new int[]{0, leftOffset,
                                         arraysBorderPoint, arraysBorderPoint + (maxLen - leftOffset)};

        int[] rightPartPoints = new int[]{leftOffset, arraysBorderPoint,
                                          leftPartPoints[3], data.length};


        int leftMax = getPointsMaxValue(data, leftPartPoints);

        int rightMin = getPointsMinValue(data, rightPartPoints);

        if (leftMax <= rightMin) {
            return leftOffset;
        } else {
            int middle = (leftBorder + rightBorder) / 2;
            if (leftOffset > 0) {
                int leftMiddleValue = data[leftPartPoints[1] - 1];
                if (leftMiddleValue == leftMax) {
                    return findLeftLen(data, arraysBorderPoint, maxLen, (leftBorder + leftOffset) / 2, leftBorder, leftOffset);
                } else {
                    return findLeftLen(data, arraysBorderPoint, maxLen, (rightBorder + leftOffset) / 2 + 1, leftOffset, rightBorder);
                }
            } else {
                return findLeftLen(data, arraysBorderPoint, maxLen, arraysBorderPoint / 2, 0, arraysBorderPoint);
            }
        }
    }

    private static int getPointsMinValue(int[] data, int[] points) {
        if (points[0] < points[1]) {
            int min = data[points[0]];

            if (points[2] < points[3]) {
                return Math.min(min, data[points[2]]);
            } else {
                return min;
            }
        } else {
            return data[points[2]];
        }
    }

    private static int getPointsMaxValue(int[] data, int[] points) {
        int max = 0;
        if (points[0] < points[1]) {
            max = data[points[1] - 1];
        }

        if (points[2] < points[3]) {
            max = Math.max(max, data[points[3] - 1]);
        }
        return max;
    }

    private static void addToArray(int[] array, StringTokenizer tokenizer, int startPos) {
        int counter = 0;
        while (tokenizer.hasMoreTokens()) {
            array[startPos + counter] = Integer.parseInt(tokenizer.nextToken());
            counter++;
        }
    }
}
