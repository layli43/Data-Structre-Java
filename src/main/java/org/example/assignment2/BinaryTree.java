package org.example.assignment2;

import org.example.structres.Iterator;
import org.example.structres.Position;
import org.example.structres.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree<T> implements Tree<T> {

    // ────────────────────────────────────────────────────────
    //  Fields
    // ────────────────────────────────────────────────────────

    protected TreeNode<T> root;
    protected int size;
    protected final Comparator<T> cmp;

    // ────────────────────────────────────────────────────────
    //  Constructor
    // ────────────────────────────────────────────────────────

    public BinaryTree(Comparator<T> cmp) {
        this.cmp  = cmp;
        this.root = null;
        this.size = 0;
    }

    // ────────────────────────────────────────────────────────
    //  Validation helper
    // ────────────────────────────────────────────────────────

    /**
     * Safely cast a Position to a TreeNode, verifying its type.
     */
    protected TreeNode<T> validate(Position<T> p) {
        if (!(p instanceof TreeNode))
            throw new IllegalArgumentException("Invalid position type");
        return (TreeNode<T>) p;
    }

    // ================================================================
    //  AbstractTree  — structural queries & traversals
    // ================================================================

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public Position<T> root() { return root; }

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

    @Override
    public Iterator<Position<T>> children(Position<T> p) {
        TreeNode<T> node = validate(p);
        List<Position<T>> ch = new ArrayList<>(2);
        if (node.getLeft()  != null) ch.add(node.getLeft());
        if (node.getRight() != null) ch.add(node.getRight());
        return listIterator(ch);
    }

    @Override
    public boolean isInternal(Position<T> p) {
        TreeNode<T> n = validate(p);
        return n.getLeft() != null || n.getRight() != null;
    }

    @Override
    public boolean isExternal(Position<T> p) {
        return !isInternal(p);
    }

    @Override
    public T replace(Position<T> p, T t) {
        TreeNode<T> node = validate(p);
        T old = node.element();
        node.setElement(t);
        return old;
    }

    /** Returns an in-order iterator over all positions. */
    @Override
    public Iterator<Position<T>> positions() {
        List<Position<T>> list = new ArrayList<>(size);
        if (root != null) inorder(root, list);
        return listIterator(list);
    }

    private void inorder(TreeNode<T> n, List<Position<T>> list) {
        if (n.getLeft()  != null) inorder(n.getLeft(), list);
        list.add(n);
        if (n.getRight() != null) inorder(n.getRight(), list);
    }

    /** Returns an in-order iterator over all elements. */
    @Override
    public Iterator<T> elements() {
        List<T> list = new ArrayList<>(size);
        Iterator<Position<T>> it = positions();
        while (it.hasNext()) list.add(it.next().element());
        return listIterator(list);
    }

    /**
     * Wraps a java.util.List in our custom Iterator interface.
     */
    private <E> Iterator<E> listIterator(List<E> list) {
        return new Iterator<E>() {
            private int index = 0;
            @Override public boolean hasNext() { return index < list.size(); }
            @Override public E next()          { return list.get(index++); }
        };
    }

    // ================================================================
    //  Tree  — addChild & remove
    // ================================================================

    /**
     * Adds data as a child of p.  Fills the left slot first,
     * then the right slot.
     *
     * @throws IllegalStateException if p already has two children
     */
    @Override
    public Position<T> addChild(Position<T> p, T data) {
        TreeNode<T> node = validate(p);
        if (node.getLeft()  == null) return addLeft(node, data);
        if (node.getRight() == null) return addRight(node, data);
        throw new IllegalStateException("Node already has two children");
    }

    /**
     * Removes position p from the tree.
     * p must have at most one child; the lone child (if any) is
     * promoted to replace p.
     *
     * @throws IllegalArgumentException if p has two children
     */
    @Override
    public T remove(Position<T> p) {
        TreeNode<T> node = validate(p);
        if (node.getLeft() != null && node.getRight() != null)
            throw new IllegalArgumentException(
                    "Cannot remove node with two children via generic remove");

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
        node.setElement(null);          // help GC
        node.setParent(node);           // defunct sentinel
        return removed;
    }

    // ================================================================
    //  Binary-tree helpers  (left / right access)
    // ================================================================

    public Position<T> left(Position<T> p)    { return validate(p).getLeft(); }
    public Position<T> right(Position<T> p)   { return validate(p).getRight(); }
    public boolean     hasLeft(Position<T> p) { return validate(p).getLeft()  != null; }
    public boolean     hasRight(Position<T> p){ return validate(p).getRight() != null; }

    public Position<T> sibling(Position<T> p) {
        TreeNode<T> node = validate(p);
        TreeNode<T> par  = node.getParent();
        if (par == null) return null;
        return (node == par.getLeft()) ? par.getRight() : par.getLeft();
    }

    protected Position<T> addLeft(TreeNode<T> node, T data) {
        if (node.getLeft() != null)
            throw new IllegalStateException("Left child already exists");
        TreeNode<T> child = new TreeNode<>(data, node);
        node.setLeft(child);
        size++;
        return child;
    }

    protected Position<T> addRight(TreeNode<T> node, T data) {
        if (node.getRight() != null)
            throw new IllegalStateException("Right child already exists");
        TreeNode<T> child = new TreeNode<>(data, node);
        node.setRight(child);
        size++;
        return child;
    }

    // ================================================================
    //  BST Search / Insert / Delete
    // ================================================================

    /**
     * Walk down from {@code node} following BST ordering.
     * Returns the node containing key, or the last node visited
     * (the would-be parent if key is absent).
     */
    protected TreeNode<T> search(TreeNode<T> node, T key) {
        if (node == null) return null;
        int c = cmp.compare(key, node.element());
        if (c == 0) return node;
        if (c < 0) {
            return (node.getLeft() == null)  ? node : search(node.getLeft(), key);
        } else {
            return (node.getRight() == null) ? node : search(node.getRight(), key);
        }
    }

    /** @return true iff the tree contains key */
    public boolean contains(T key) {
        if (root == null) return false;
        TreeNode<T> n = search(root, key);
        return n != null && cmp.compare(key, n.element()) == 0;
    }

    /** @return the Position storing key, or null */
    public Position<T> find(T key) {
        if (root == null) return null;
        TreeNode<T> n = search(root, key);
        return (n != null && cmp.compare(key, n.element()) == 0) ? n : null;
    }

    /**
     * Insert key into the BST.  Duplicates overwrite the
     * existing element.
     *
     * @return the Position of the inserted (or updated) node
     */
    public Position<T> insert(T key) {
        if (root == null) {
            addRoot(key);
            return root;
        }
        TreeNode<T> p = search(root, key);
        int c = cmp.compare(key, p.element());
        if (c == 0) {                                   // duplicate
            p.setElement(key);
            return p;
        }
        TreeNode<T> child = new TreeNode<>(key, p);
        if (c < 0) p.setLeft(child);
        else        p.setRight(child);
        size++;
        afterInsert(child);                             // hook for AVL
        return child;
    }

    /**
     * Delete the node containing key.
     *
     * Three cases:
     *   0 children  →  unlink directly
     *   1 child     →  promote the child
     *   2 children  →  swap with in-order successor, then
     *                   remove the successor
     *
     * @return the removed element, or null if key was absent
     */
    public T delete(T key) {
        if (root == null) return null;
        TreeNode<T> n = search(root, key);
        if (n == null || cmp.compare(key, n.element()) != 0) return null;

        TreeNode<T> rebalancePoint = n.getParent();

        // ── two-child case ──────────────────────────────
        if (n.getLeft() != null && n.getRight() != null) {
            TreeNode<T> succ = minNode(n.getRight());
            n.setElement(succ.element());
            rebalancePoint = succ.getParent();
            n = succ;                                   // now remove succ
        }

        // ── zero- or one-child case ─────────────────────
        TreeNode<T> child = (n.getLeft() != null)
                ? n.getLeft() : n.getRight();
        if (child != null) child.setParent(n.getParent());

        if (n.getParent() == null) {
            root = child;
        } else if (n == n.getParent().getLeft()) {
            n.getParent().setLeft(child);
        } else {
            n.getParent().setRight(child);
        }
        size--;
        T removed = n.element();
        afterDelete(rebalancePoint);                    // hook for AVL
        return removed;
    }

    /** Find the minimum node in the subtree rooted at n. */
    protected TreeNode<T> minNode(TreeNode<T> n) {
        while (n.getLeft() != null) n = n.getLeft();
        return n;
    }

    // ================================================================
    //  Hooks — overridden by AVLTree
    // ================================================================

    protected void afterInsert(TreeNode<T> node) { /* no-op */ }
    protected void afterDelete(TreeNode<T> node) { /* no-op */ }

    // ================================================================
    //  Rotation primitives  (used by AVLTree)
    // ================================================================

    /**
     * Right-rotate around x.
     * <pre>
     *        x              y
     *       / \            / \
     *      y   C    →    A    x
     *     / \                / \
     *    A   B              B   C
     * </pre>
     */
    protected void rotateRight(TreeNode<T> x) {
        TreeNode<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) y.getRight().setParent(x);
        y.setParent(x.getParent());
        if      (x.getParent() == null)             root = y;
        else if (x == x.getParent().getLeft())      x.getParent().setLeft(y);
        else                                        x.getParent().setRight(y);
        y.setRight(x);
        x.setParent(y);
    }

    /**
     * Left-rotate around x.
     * <pre>
     *      x                y
     *     / \              / \
     *    A   y      →    x    C
     *       / \         / \
     *      B   C       A   B
     * </pre>
     */
    protected void rotateLeft(TreeNode<T> x) {
        TreeNode<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) y.getLeft().setParent(x);
        y.setParent(x.getParent());
        if      (x.getParent() == null)             root = y;
        else if (x == x.getParent().getLeft())      x.getParent().setLeft(y);
        else                                        x.getParent().setRight(y);
        y.setLeft(x);
        x.setParent(y);
    }

    // ================================================================
    //  Utility
    // ================================================================

    /** Compute height by recursive descent (for testing / experiments). */
    public int height() { return heightOf(root); }

    private int heightOf(TreeNode<T> n) {
        if (n == null) return -1;
        return 1 + Math.max(heightOf(n.getLeft()), heightOf(n.getRight()));
    }
}


