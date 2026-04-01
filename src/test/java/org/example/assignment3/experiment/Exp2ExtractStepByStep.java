package org.example.assignment3.experiment;

import org.example.assignment3.heap.ArrayHeap;
import org.example.structures.Entry;

public class Exp2ExtractStepByStep {
    public static void main(String[] args) {
        System.out.println("\n=== Experiment 2: Step-by-Step extractMin (downHeap) ===\n");

        int[] keys = {30, 10, 50, 5, 20, 40, 15};
        ArrayHeap<Integer, String> heap = new ArrayHeap<>(Integer::compare);
        for (int k : keys) heap.insert(new Entry<>(k, "v" + k));

        System.out.println("Initial heap: " + heap + "\n");

        StringBuilder sorted = new StringBuilder();
        while (!heap.isEmpty()) {
            Entry<Integer, String> min = heap.extractMin();
            if (sorted.length() > 0) sorted.append(", ");
            sorted.append(min.getKey());

            System.out.printf("  extractMin()=%2d  heap=%s%n sorted=%s%n",
                    min.getKey(), heap, sorted);
        }

        System.out.println("\n  Sorted output: [" + sorted + "]");

        System.out.println("\nAnalysis:");
        System.out.println("  extractMin() is O(log n) — downHeap walks one root-to-leaf");
        System.out.println("  path with 2 comparisons per level.");
        System.out.println("  The recursive downHeap adds a stack frame per level; converting");
        System.out.println("  to a while-loop (like upHeap) would save that overhead.");
        System.out.println("  Extracting all n elements = heap sort, O(n log n) total.");
    }
}
