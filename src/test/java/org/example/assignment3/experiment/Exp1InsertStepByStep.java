package org.example.assignment3.experiment;

import org.example.assignment3.heap.ArrayHeap;
import org.example.structures.Entry;

import java.util.Arrays;

public class Exp1InsertStepByStep {
    public static void main(String[] args) {
        System.out.println("\n=== Experiment 1: Step-by-Step Insert (upHeap) ===\n");

        int[] keys = {45, 20, 35, 10, 50, 30, 5};
        ArrayHeap<Integer, String> heap = new ArrayHeap<>(Integer::compare);

        System.out.println("Input: " + Arrays.toString(keys) + "\n");

        for (int key : keys) {
            heap.insert(new Entry<>(key, "v" + key));
            System.out.printf("  insert(%2d)    heap=%s%n",
                    key, heap);
        }

        System.out.println("\n  peek() = " + heap.peek());

        System.out.println("\nAnalysis:");
        System.out.println("  insert() is O(log n) — upHeap traverses at most one");
        System.out.println("  root-to-leaf path (height = floor(log2 n)).");
        System.out.println("  Inserting 5 (the global min) causes the most swaps");
        System.out.println("  because it must bubble all the way to index 0.");
    }
}
