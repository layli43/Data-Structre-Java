package org.example.structres;

public class VectorIterator<T> implements Iterator<T> {

    private int index;
    private Vector<T> vector;

    public VectorIterator(Vector<T> vector) {
        this.vector = vector;
        index = 0;
    }

    public boolean hasNext() {
        return index < vector.size();
    }

    public T next() {
        return vector.elemAtRank(index++);
    }
}
