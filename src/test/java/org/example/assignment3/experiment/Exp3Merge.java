package org.example.assignment3.experiment;

import org.example.assignment3.heap.ArrayHeap;
import org.example.structures.Entry;

import java.util.Arrays;
import java.util.Random;

public class Exp3Merge {
    public static void main(String[] args) {
        System.out.println("\n=== Experiment 7: Heap Merge ===\n");

        // Small visible demo
        System.out.println("-- Small demo --\n");

        ArrayHeap<Integer, Integer> a = buildHeap(new int[]{10, 30, 50, 70});
        ArrayHeap<Integer, Integer> b = buildHeap(new int[]{5, 20, 40, 60});
        System.out.println("  Heap A: " + a);
        System.out.println("  Heap B: " + b);

        ArrayHeap<Integer, Integer> mA = mergeExtractAll(
                buildHeap(new int[]{10,30,50,70}), buildHeap(new int[]{5,20,40,60}));
        System.out.println("  Strategy A result: " + mA);
        System.out.println("  Sorted: " + drainSorted(mergeExtractAll(
                buildHeap(new int[]{10,30,50,70}), buildHeap(new int[]{5,20,40,60}))));

        ArrayHeap<Integer, Integer> mB = mergeIntoLarger(
                buildHeap(new int[]{10,30,50,70}), buildHeap(new int[]{5,20,40,60}));
        System.out.println("  Strategy B result: " + mB);
        System.out.println("  Sorted: " + drainSorted(mergeIntoLarger(
                buildHeap(new int[]{10,30,50,70}), buildHeap(new int[]{5,20,40,60}))));

        // Scaling: equal-sized heaps
        System.out.println("\n-- Scaling: equal-sized heaps (m = n) --\n");
        System.out.printf("  %-8s %12s %12s %8s%n", "m=n", "A (ms)", "B (ms)", "Speedup");
        System.out.println("  " + "-".repeat(44));

        Random rng = new Random(42);
        int[] sizes = {1_000, 5_000, 10_000, 50_000, 100_000};

        for (int n : sizes) {
            int[] d1 = randomData(n, rng), d2 = randomData(n, rng);

            long t0 = System.nanoTime();
            mergeExtractAll(buildHeap(d1), buildHeap(d2));
            double msA = (System.nanoTime() - t0) / 1e6;

            t0 = System.nanoTime();
            mergeIntoLarger(buildHeap(d1), buildHeap(d2));
            double msB = (System.nanoTime() - t0) / 1e6;

            System.out.printf("  %-8d %12.3f %12.3f %7.1fx%n",
                    n, msA, msB, msA / Math.max(msB, 0.001));
        }

        // Scaling: unequal heaps
        System.out.println("\n-- Scaling: unequal heaps (n=10000, varying m) --\n");
        System.out.printf("  %-6s %-6s %12s %12s %8s%n", "n", "m", "A (ms)", "B (ms)", "Speedup");
        System.out.println("  " + "-".repeat(48));

        int large = 10_000;
        int[] largeDat = randomData(large, rng);
        int[] smallSizes = {10, 50, 100, 500, 1_000, 5_000, 10_000};

        for (int m : smallSizes) {
            int[] smallDat = randomData(m, rng);

            long t0 = System.nanoTime();
            mergeExtractAll(buildHeap(largeDat), buildHeap(smallDat));
            double msA = (System.nanoTime() - t0) / 1e6;

            t0 = System.nanoTime();
            mergeIntoLarger(buildHeap(largeDat), buildHeap(smallDat));
            double msB = (System.nanoTime() - t0) / 1e6;

            System.out.printf("  %-6d %-6d %12.3f %12.3f %7.1fx%n",
                    large, m, msA, msB, msA / Math.max(msB, 0.001));
        }

        System.out.println("\nAnalysis:");
        System.out.println("  Strategy B keeps the larger heap intact — avoids re-heapifying");
        System.out.println("  elements already in valid order. When m << n, B is much faster.");
        System.out.println("  Neither achieves O(m+n); that requires a mergeable heap");
        System.out.println("  (binomial heap, leftist heap) where merge is the primitive.");
    }

    // Build a new heap
    static ArrayHeap<Integer, Integer> mergeExtractAll(
            ArrayHeap<Integer, Integer> h1, ArrayHeap<Integer, Integer> h2) {
        ArrayHeap<Integer, Integer> merged = new ArrayHeap<>(Integer::compare);
        while (!h1.isEmpty()) { Entry<Integer,Integer> e = h1.extractMin(); merged.insert(new Entry<>(e.getKey(), e.getValue())); }
        while (!h2.isEmpty()) { Entry<Integer,Integer> e = h2.extractMin(); merged.insert(new Entry<>(e.getKey(), e.getValue())); }
        return merged;
    }

    static ArrayHeap<Integer, Integer> mergeIntoLarger(
            ArrayHeap<Integer, Integer> h1, ArrayHeap<Integer, Integer> h2) {
        ArrayHeap<Integer, Integer> larger  = h1.size() >= h2.size() ? h1 : h2;
        ArrayHeap<Integer, Integer> smaller = h1.size() >= h2.size() ? h2 : h1;
        while (!smaller.isEmpty()) { Entry<Integer,Integer> e = smaller.extractMin(); larger.insert(new Entry<>(e.getKey(), e.getValue())); }
        return larger;
    }

    static ArrayHeap<Integer, Integer> buildHeap(int[] data) {
        ArrayHeap<Integer, Integer> h = new ArrayHeap<>(Integer::compare);
        for (int k : data) h.insert(new Entry<>(k, k));
        return h;
    }

    static String drainSorted(ArrayHeap<Integer, Integer> h) {
        StringBuilder sb = new StringBuilder("[");
        while (!h.isEmpty()) {
            if (sb.length() > 1) sb.append(", ");
            sb.append(h.extractMin().getKey());
        }
        return sb.append("]").toString();
    }

    static int[] randomData(int n, Random rng) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rng.nextInt(n * 4);
        return a;
    }
}
