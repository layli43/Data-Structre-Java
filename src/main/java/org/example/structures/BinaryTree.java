package org.example.structures;

public interface BinaryTree<T> extends AbstractTree<T> {
    public Position<T> leftChild(Position<T> p);
    public Position<T> rightChild(Position<T> p);
    public Position<T> sibling(Position<T> p);
    public void addLeftChild(Position<T> p, T data);
    public void addRightChild(Position<T> p, T data);
    public T remove(Position<T> p);
}
