package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class MeetingCalendar {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());

        List<Lesson> lessons = new ArrayList<>();

        for (int i = 0; i < dataLength; i++) {
            lessons.add(Lesson.fromString(reader.readLine()));
        }

        lessons.sort(new Comparator<Lesson>() {
            @Override
            public int compare(Lesson o1, Lesson o2) {
                int compareByEnd = Double.compare(o1.getEndTime(), o2.getEndTime()) ;
                return compareByEnd == 0 ? Double.compare(o1.getStartTime(), o2.getStartTime()) : compareByEnd;
            }
        });

        List<Lesson> result = new ArrayList<>();
        Lesson lastAcceptedLesson = lessons.getFirst();
        result.add(lastAcceptedLesson);

        for (int i = 1; i < lessons.size(); i++) {
            Lesson current = lessons.get(i);
            if (current.getStartTime() >= lastAcceptedLesson.getEndTime()) {
                result.add(current);
                lastAcceptedLesson = current;
            }
        }


        System.out.println(buildResult(result));
    }

    private static String buildResult(List<Lesson> lessons) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add(String.valueOf(lessons.size()));
        lessons.stream().map(Lesson::toString).forEach(result::add);
        return result.toString();
    }


    static class Lesson {
        private static final DecimalFormat df = new DecimalFormat("0.##");
        private final double startTime;
        private final double endTime;


        Lesson(double startTime, double endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public double getStartTime() {
            return startTime;
        }

        public double getEndTime() {
            return endTime;
        }

        public static Lesson fromString(String s) {
            String[]  split = s.split(" ");

            return new Lesson(Double.valueOf(split[0]), Double.valueOf(split[1]));
        }

        @Override
        public String toString() {
            return df.format(startTime) + " " + df.format(endTime);
        }
    }
}
