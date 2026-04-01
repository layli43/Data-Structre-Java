package org.example.assignment2;

import org.example.structures.Position;

import java.util.Comparator;

public class AvlTree<T> extends BinaryTree<T> {

    public AvlTree(Comparator<T> cmp) {
        super(cmp);
    }

    // Returns height of a node, or -1 if null
    private int h(TreeNode<T> n) {
        return (n == null) ? -1 : n.getHeight();
    }

    // Updates the height of a node based on its children
    private void updateHeight(TreeNode<T> n) {
        n.setHeight(1 + Math.max(h(n.getLeft()), h(n.getRight())));
    }

    // Calculates balance factor (left height - right height)
    private int balanceFactor(TreeNode<T> n) {
        return h(n.getLeft()) - h(n.getRight());
    }

    // Rebalances the tree starting from a given node up to the root
    private void rebalance(TreeNode<T> node) {
        while (node != null) {
            updateHeight(node);
            int bf = balanceFactor(node);

            if (bf > 1) {
                // Left-heavy case
                if (balanceFactor(node.getLeft()) < 0) {
                    // Left-Right case
                    rotateLeft(node.getLeft());
                }
                // Left-Left case
                rotateRight(node);
                node = node.getParent();

            } else if (bf < -1) {
                // Right-heavy case
                if (balanceFactor(node.getRight()) > 0) {
                    // Right-Left case
                    rotateRight(node.getRight());
                }
                // Right-Right case
                rotateLeft(node);
                node = node.getParent();
            }

            // Update height and move up the tree
            if (node != null) updateHeight(node);
            node = (node != null) ? node.getParent() : null;
        }
    }

    // Performs right rotation and updates heights
    @Override
    protected void rotateRight(TreeNode<T> x) {
        super.rotateRight(x);
        updateHeight(x);
        updateHeight(x.getParent());
    }

    // Performs left rotation and updates heights
    @Override
    protected void rotateLeft(TreeNode<T> x) {
        super.rotateLeft(x);
        updateHeight(x);
        updateHeight(x.getParent());
    }

    // Called after insertion to restore AVL balance
    @Override
    protected void afterInsert(TreeNode<T> node) {
        rebalance(node);
    }

    // Called after deletion to restore AVL balance
    @Override
    protected void afterDelete(TreeNode<T> node) {
        rebalance(node);
    }

    // Add left child
    @Override
    protected Position<T> addLeft(TreeNode<T> node, T data) {
        if (node.getLeft() != null)
            throw new IllegalStateException("Left child already exists");
        TreeNode<T> child = new TreeNode<>(data, node);
        node.setLeft(child);
        size++;
        return child;
    }
}