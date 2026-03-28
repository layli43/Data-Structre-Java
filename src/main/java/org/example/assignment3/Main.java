package org.example.assignment3;

import org.example.assignment3.experiment.Experiment;
import org.example.structures.*;

public class Main {

    public static void main(String[] args) {
        //Run performance experiment
        Experiment.run();
        System.out.println("\nHeap Priority Queue Demo: ");

        PriorityQueue<Integer, String> pq =
                new HeapPriorityQueue<>(Integer::compare);

        pq.insert(3, "Task C");
        pq.insert(1, "Task A");
        pq.insert(2, "Task B");

        while (!pq.isEmpty()) {
            Entry<Integer, String> entry = pq.removeMin();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}