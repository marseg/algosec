package com.algosec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sergey.dolzhenok@gmail.com">Sergey Dolzhenok</a>
 */
public class Exam {
    public static void main(String[] args) throws Exception {

        List<Segment> segments = createListOfSegments(10000, 10000);

        firstWay(segments);
        secondWay(segments);
    }

    /**
     * Go through all segments and increment counter for each point in each segment.
     */
    private static void firstWay(List<Segment> segments) {
        System.out.println("First way");
        long startTime = System.currentTimeMillis();

        // Map with counters
        Map<Integer, Integer> pointsCount = new HashMap<>();
        int maxValue = 0;
        int minKey = 0;

        //long comparesCount = 0;
        //long writesCount = 0;
        //long iterationsCount = 0;

        //Iterate all segments
        for (Segment segment : segments) {
            // Increment a counter for each point in the segment
            for (int x = segment.getStartX(); x <= segment.getEndX(); x++) {
                int value = 0;
                if (!pointsCount.containsKey(x)) {
                    pointsCount.put(x, 0);
                    //writesCount++;
                } else {
                    value = pointsCount.get(x);
                }
                //comparesCount++;

                pointsCount.put(x, value + 1);
                //writesCount++;

                //iterationsCount++;
            }
            //iterationsCount++;
        }

        // Choose the most "popular"
        for (Map.Entry<Integer, Integer> entry : pointsCount.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                minKey = entry.getKey();

                //comparesCount++;
                //writesCount += 2;
            } else if (entry.getValue() == maxValue && entry.getKey() < minKey) {
                minKey = entry.getKey();

                //comparesCount += 2;
                //writesCount++;
            }
            //iterationsCount++;
        }

        System.out.println(minKey + " -> " + maxValue);

        System.out.println(System.currentTimeMillis() - startTime);
        //System.out.println("Compares: " + comparesCount);
        //System.out.println("Writes: " + writesCount);
        //System.out.println("Iterations: " + iterationsCount);
    }

    /**
     * Go through all points on axe (from min to max) and count how much segments contain this point.
     */
    private static void secondWay(List<Segment> segments) {
        System.out.println("Second way");
        long startTime = System.currentTimeMillis();

        // Map with counters
        Map<Integer, Integer> pointsCount = new HashMap<>();
        int maxValue = 0;
        int minKey = 0;

        //long comparesCount = 0;
        //long writesCount = 0;
        //long iterationsCount = 0;

        //Find the min and max points
        int minX = segments.get(0).getStartX();
        int maxX = segments.get(0).getEndX();
        for (Segment segment : segments) {
            if (segment.getStartX() < minX) {
                minX = segment.getStartX();

                //writesCount++;
            }
            //comparesCount++;

            if (segment.getEndX() > maxX) {
                maxX = segment.getEndX();

                //writesCount++;
            }
            //comparesCount++;

            // iterationsCount++;
        }

        //Iterate all points
        for (int x = minX; x <= maxX; x++) {
            pointsCount.put(x, 0);
            //writesCount++;

            // Count segments with this point
            for (Segment segment : segments) {
                if (x >= segment.getStartX() && x <= segment.getEndX()) {
                    pointsCount.put(x, pointsCount.get(x) + 1);

                    //writesCount++;
                }
                //comparesCount += 2;
                //iterationsCount++;
            }
            //iterationsCount++;
        }

        // Choose the most "popular"
        for (Map.Entry<Integer, Integer> entry : pointsCount.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                minKey = entry.getKey();

                //comparesCount++;
                //writesCount += 2;
            } else if (entry.getValue() == maxValue && entry.getKey() < minKey) {
                minKey = entry.getKey();

                //comparesCount += 2;
                //writesCount++;
            }
            //iterationsCount++;
        }

        System.out.println(minKey + " -> " + maxValue);

        System.out.println(System.currentTimeMillis() - startTime);
        //System.out.println("Compares: " + comparesCount);
        //System.out.println("Writes: " + writesCount);
        //System.out.println("Iterations: " + iterationsCount);
    }

    private static List<Segment> createListOfSegments(int size, int max) {
        List<Segment> segments = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int startX = (int) (Math.random() * max);
            int endX = (int) (Math.random() * max);
            if (startX > endX) { // need to swap
                int tmp = endX;
                endX = startX;
                startX = tmp;
            }
            Segment segment = new Segment(startX, endX);
            segments.add(segment);
        }

        return segments;
    }
}

class Segment {
    private int startX;
    private int endX;

    public Segment(int startX, int endX) {
        this.startX = startX;
        this.endX = endX;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }
}
