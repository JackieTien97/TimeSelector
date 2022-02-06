package org.apache.iotdb.experiment;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

public class Main {

    private static final int TOTAL_COUNT = 10_000_000;
    private static final long SEED = 123456;


    public static void main(String[] args) {
        // Prior version
        long startTime = System.nanoTime();
        Random random = new Random(SEED);
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>(TOTAL_COUNT);
        for (int i = 0; i < TOTAL_COUNT; i++) {
            priorityQueue.add(random.nextLong());
        }
        long sum = 0;
        while (!priorityQueue.isEmpty()) {
            sum += priorityQueue.poll();
        }
        System.out.println("Sum is: " + sum);
        System.out.println("PriorityQueue<Long> cost " + (System.nanoTime() - startTime) / 1_000_000 + "ms.");

        // TreeSet version
        startTime = System.nanoTime();
        random = new Random(SEED);
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < TOTAL_COUNT; i++) {
            treeSet.add(random.nextLong());
        }
        sum = 0;
        while (!treeSet.isEmpty()) {
            sum += treeSet.pollFirst();
        }
        System.out.println("Sum is: " + sum);
        System.out.println("TreeSet<Long> cost " + (System.nanoTime() - startTime) / 1_000_000 + "ms.");

        // iotdb TimeSelector version
        startTime = System.nanoTime();
        random = new Random(SEED);
        TimeSelector selector = new TimeSelector(TOTAL_COUNT, true);
        for (int i = 0; i < TOTAL_COUNT; i++) {
            selector.add(random.nextLong());
        }
        sum = 0;
        while (!selector.isEmpty()) {
            sum += selector.pollFirst();
        }
        System.out.println("Sum is: " + sum);
        System.out.println("TimeSelector cost " + (System.nanoTime() - startTime) / 1_000_000 + "ms.");
    }
}
