package org.example.assignment1.Queue;

public interface QueueInterface<T> {
    void enqueue(T item); // Add to the end
    T dequeue();          // Remove from the front
    T peek() throws Exception;             // View the front item
    boolean isEmpty();
    int size();
}
