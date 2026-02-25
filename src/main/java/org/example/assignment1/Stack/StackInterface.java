package org.example.assignment1.Stack;

public interface StackInterface<T> {
    void push(T item);

    T pop();

    T top();

    boolean isEmpty();

    int size();
}
