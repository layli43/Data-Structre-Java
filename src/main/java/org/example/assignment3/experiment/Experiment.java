package org.example.assignment3.experiment;

import org.example.structures.*;

public class Experiment {

    public static void run() {

        int n = 10000;

        testHeap(n);
        testSorted(n);
        testUnsorted(n);
    }

    private static void testHeap(int n) {
        PriorityQueue<Integer, Integer> pq =
                new HeapPriorityQueue<>(Integer::compare);

        long start = System.nanoTime();

        for (int i = 0; i < n; i++)
            pq.insert((int)(Math.random()*100000), i);

        for (int i = 0; i < n; i++)
            pq.removeMin();

        long end = System.nanoTime();

        System.out.println("Heap Priority Queue: " + (end - start));
    }

    private static void testSorted(int n) {
        PriorityQueue<Integer, Integer> pq =
                new SortedPriorityQueue<>(Integer::compare);

        long start = System.nanoTime();

        for (int i = 0; i < n; i++)
            pq.insert((int)(Math.random()*100000), i);

        for (int i = 0; i < n; i++)
            pq.removeMin();

        long end = System.nanoTime();

        System.out.println("Sorted Priority Queue: " + (end - start));
    }

    private static void testUnsorted(int n) {
        PriorityQueue<Integer, Integer> pq =
                new UnsortedPriorityQueue<>(Integer::compare);

        long start = System.nanoTime();

        for (int i = 0; i < n; i++)
            pq.insert((int)(Math.random()*100000), i);

        for (int i = 0; i < n; i++)
            pq.removeMin();

        long end = System.nanoTime();

        System.out.println("Unsorted Priority Queue: " + (end - start));
    }
}