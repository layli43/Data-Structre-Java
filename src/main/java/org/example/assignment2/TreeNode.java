package org.example.assignment2;

import org.example.structures.Position;

public class TreeNode<T> implements Position<T> {

    private T element;
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int height;    // used for AVL balancing

    public TreeNode(T element, TreeNode<T> parent) {
        this.element = element;
        this.parent  = parent;
        this.height  = 0;
    }

    // Positions
    @Override
    public T element() { return element; }

    // Accessors
    public TreeNode<T> getParent() { return parent; }
    public TreeNode<T> getLeft()   { return left; }
    public TreeNode<T> getRight()  { return right; }
    public int         getHeight() { return height; }

    // Mutators
    public void setElement(T e)           { this.element = e; }
    public void setParent(TreeNode<T> p)  { this.parent = p; }
    public void setLeft(TreeNode<T> l)    { this.left = l; }
    public void setRight(TreeNode<T> r)   { this.right = r; }
    public void setHeight(int h)          { this.height = h; }

    @Override
    public String toString() { return "Node(" + element + ")"; }
}
