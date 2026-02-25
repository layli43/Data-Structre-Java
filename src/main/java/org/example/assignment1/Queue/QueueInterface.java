package org.example.assignment1.Queue;

public interface QueueInterface<T> {
    void enqueue(T item); // Add to the end
    T dequeue();          // Remove from the front
    boolean isEmpty();
    int size();
}
