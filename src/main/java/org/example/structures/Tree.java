package org.example.structures;

public interface Tree<T> extends AbstractTree<T> {
    public Position<T> addChild(Position<T> p, T data);
    public T remove(Position<T> p); 
}
