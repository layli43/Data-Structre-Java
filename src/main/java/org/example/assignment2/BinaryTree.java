package org.example.assignment2;

import org.example.structures.Iterator;
import org.example.structures.Position;
import org.example.structures.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree<T> implements Tree<T> {

    // Root node of the tree and size tracker
    protected TreeNode<T> root;
    protected int size;

    // Comparator used for ordering elements (BST property)
    protected final Comparator<T> cmp;

    // Constructor
    public BinaryTree(Comparator<T> cmp) {
        this.cmp  = cmp;
        this.root = null;
        this.size = 0;
    }

    // Ensures a position is actually a TreeNode
    protected TreeNode<T> validate(Position<T> p) {
        if (!(p instanceof TreeNode))
            throw new IllegalArgumentException("Invalid position type");
        return (TreeNode<T>) p;
    }

    // Basic tree methods
    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public Position<T> root() { return root; }

    // Adds the root node
    @Override
    public void addRoot(T data) {
        if (root != null)
            throw new IllegalStateException("Tree already has a root");
        root = new TreeNode<>(data, null);
        size = 1;
    }

    @Override
    public boolean isRoot(Position<T> p) {
        return validate(p) == root;
    }

    @Override
    public Position<T> parent(Position<T> p) {
        return validate(p).getParent();
    }

    // Returns children (max 2 in binary tree)
    @Override
    public Iterator<Position<T>> children(Position<T> p) {
        TreeNode<T> node = validate(p);
        List<Position<T>> ch = new ArrayList<>(2);
        if (node.getLeft()  != null) ch.add(node.getLeft());
        if (node.getRight() != null) ch.add(node.getRight());
        return listIterator(ch);
    }

    // Checks if node has at least one child
    @Override
    public boolean isInternal(Position<T> p) {
        TreeNode<T> n = validate(p);
        return n.getLeft() != null || n.getRight() != null;
    }

    @Override
    public boolean isExternal(Position<T> p) {
        return !isInternal(p);
    }

    // Replace value stored at a node
    @Override
    public T replace(Position<T> p, T t) {
        TreeNode<T> node = validate(p);
        T old = node.element();
        node.setElement(t);
        return old;
    }

    // In-order traversal of positions
    @Override
    public Iterator<Position<T>> positions() {
        List<Position<T>> list = new ArrayList<>(size);
        if (root != null) inorder(root, list);
        return listIterator(list);
    }

    // Recursive inorder traversal
    private void inorder(TreeNode<T> n, List<Position<T>> list) {
        if (n.getLeft()  != null) inorder(n.getLeft(), list);
        list.add(n);
        if (n.getRight() != null) inorder(n.getRight(), list);
    }

    // Returns elements using inorder traversal
    @Override
    public Iterator<T> elements() {
        List<T> list = new ArrayList<>(size);
        Iterator<Position<T>> it = positions();
        while (it.hasNext()) list.add(it.next().element());
        return listIterator(list);
    }

    // Helper to convert list into custom iterator
    private <E> Iterator<E> listIterator(List<E> list) {
        return new Iterator<E>() {
            private int index = 0;
            @Override public boolean hasNext() { return index < list.size(); }
            @Override public E next()          { return list.get(index++); }
        };
    }

    // Adds a child (left first, then right)
    @Override
    public Position<T> addChild(Position<T> p, T data) {
        TreeNode<T> node = validate(p);
        if (node.getLeft()  == null) return addLeft(node, data);
        if (node.getRight() == null) return addRight(node, data);
        throw new IllegalStateException("Node already has two children");
    }

    // Removes a node with at most one child
    @Override
    public T remove(Position<T> p) {
        TreeNode<T> node = validate(p);

        if (node.getLeft() != null && node.getRight() != null)
            throw new IllegalArgumentException("Cannot remove node with two children");

        TreeNode<T> child = (node.getLeft() != null)
                ? node.getLeft() : node.getRight();

        if (child != null) child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            TreeNode<T> par = node.getParent();
            if (node == par.getLeft()) par.setLeft(child);
            else                       par.setRight(child);
        }

        size--;
        T removed = node.element();
        node.setElement(null);
        node.setParent(node);
        return removed;
    }

    // Left/right access helpers
    public Position<T> left(Position<T> p)    { return validate(p).getLeft(); }
    public Position<T> right(Position<T> p)   { return validate(p).getRight(); }
    public boolean hasLeft(Position<T> p)     { return validate(p).getLeft()  != null; }
    public boolean hasRight(Position<T> p)    { return validate(p).getRight() != null; }

    // Returns sibling node
    public Position<T> sibling(Position<T> p) {
        TreeNode<T> node = validate(p);
        TreeNode<T> par  = node.getParent();
        if (par == null) return null;
        return (node == par.getLeft()) ? par.getRight() : par.getLeft();
    }

    // Add left child
    protected Position<T> addLeft(TreeNode<T> node, T data) {
        if (node.getLeft() != null)
            throw new IllegalStateException("Left child already exists");
        TreeNode<T> child = new TreeNode<>(data, node);
        node.setLeft(child);
        size++;
        return child;
    }

    // Add right child
    protected Position<T> addRight(TreeNode<T> node, T data) {
        if (node.getRight() != null)
            throw new IllegalStateException("Right child already exists");
        TreeNode<T> child = new TreeNode<>(data, node);
        node.setRight(child);
        size++;
        return child;
    }

    // BST search (recursive)
    protected TreeNode<T> search(TreeNode<T> node, T key) {
        if (node == null) return null;
        int c = cmp.compare(key, node.element());

        if (c == 0) return node;

        if (c < 0)
            return (node.getLeft() == null) ? node : search(node.getLeft(), key);
        else
            return (node.getRight() == null) ? node : search(node.getRight(), key);
    }

    // Check if key exists
    public boolean contains(T key) {
        if (root == null) return false;
        TreeNode<T> n = search(root, key);
        return n != null && cmp.compare(key, n.element()) == 0;
    }

    // Find node containing key
    public Position<T> find(T key) {
        if (root == null) return null;
        TreeNode<T> n = search(root, key);
        return (n != null && cmp.compare(key, n.element()) == 0) ? n : null;
    }

    // Insert into BST
    public Position<T> insert(T key) {
        if (root == null) {
            addRoot(key);
            return root;
        }

        TreeNode<T> p = search(root, key);
        int c = cmp.compare(key, p.element());

        if (c == 0) {
            p.setElement(key);
            return p;
        }

        TreeNode<T> child = new TreeNode<>(key, p);

        if (c < 0) p.setLeft(child);
        else       p.setRight(child);

        size++;
        afterInsert(child);
        return child;
    }

    // Delete node from BST
    public T delete(T key) {
        if (root == null) return null;

        TreeNode<T> n = search(root, key);
        if (n == null || cmp.compare(key, n.element()) != 0) return null;

        TreeNode<T> rebalancePoint = n.getParent();

        // Two children case
        if (n.getLeft() != null && n.getRight() != null) {
            TreeNode<T> succ = minNode(n.getRight());
            n.setElement(succ.element());
            rebalancePoint = succ.getParent();
            n = succ;
        }

        // One or zero child
        TreeNode<T> child = (n.getLeft() != null)
                ? n.getLeft() : n.getRight();

        if (child != null) child.setParent(n.getParent());

        if (n.getParent() == null) root = child;
        else if (n == n.getParent().getLeft()) n.getParent().setLeft(child);
        else n.getParent().setRight(child);

        size--;
        T removed = n.element();
        afterDelete(rebalancePoint);
        return removed;
    }

    // Find minimum node in subtree
    protected TreeNode<T> minNode(TreeNode<T> n) {
        while (n.getLeft() != null) n = n.getLeft();
        return n;
    }

    // Hooks for AVL (empty here)
    protected void afterInsert(TreeNode<T> node) { }
    protected void afterDelete(TreeNode<T> node) { }

    // Right rotation
    protected void rotateRight(TreeNode<T> x) {
        TreeNode<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) y.getRight().setParent(x);

        y.setParent(x.getParent());

        if (x.getParent() == null) root = y;
        else if (x == x.getParent().getLeft()) x.getParent().setLeft(y);
        else x.getParent().setRight(y);

        y.setRight(x);
        x.setParent(y);
    }

    // Left rotation
    protected void rotateLeft(TreeNode<T> x) {
        TreeNode<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) y.getLeft().setParent(x);

        y.setParent(x.getParent());

        if (x.getParent() == null) root = y;
        else if (x == x.getParent().getLeft()) x.getParent().setLeft(y);
        else x.getParent().setRight(y);

        y.setLeft(x);
        x.setParent(y);
    }

    // Compute height recursively (for testing)
    public int height() { return heightOf(root); }

    private int heightOf(TreeNode<T> n) {
        if (n == null) return -1;
        return 1 + Math.max(heightOf(n.getLeft()), heightOf(n.getRight()));
    }
}