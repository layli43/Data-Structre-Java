package org.example.assignment1.Stack;

/**
 * An Stack implemented by ArrayList
 * @param <T>
 */
public class ArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        stack = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void push(T element) {
        if (size == stack.length) {
            // Tuning: Standard array-based stacks often resize here
            throw new RuntimeException("Stack Overflow");
        }
        stack[size++] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;
        T element = stack[--size];
        stack[size] = null; // Help Garbage Collection
        return element;
    }

    @Override
    public T top() {
        if (isEmpty()) return null;
        return stack[size - 1];
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("" + size + "\t");
        for (int i = 0; i < size; i++) {
            output.append(stack[i]+" ");
        }
        return output.toString();
    }
}
