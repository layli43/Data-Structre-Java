package org.example.assignment2;

import java.util.Comparator;

public class AvlTree<T> extends BinaryTree<T> {

    public AvlTree(Comparator<T> cmp) {
        super(cmp);
    }

    // ================================================================
    //  Height helpers
    // ================================================================

    /** Height of a possibly-null node (−1 for null). */
    private int h(TreeNode<T> n) {
        return (n == null) ? -1 : n.getHeight();
    }

    /** Recompute and store the height of n from its children. */
    private void updateHeight(TreeNode<T> n) {
        n.setHeight(1 + Math.max(h(n.getLeft()), h(n.getRight())));
    }

    /** Balance factor = height(left) − height(right). */
    private int balanceFactor(TreeNode<T> n) {
        return h(n.getLeft()) - h(n.getRight());
    }

    // ================================================================
    //  Rebalance walk
    // ================================================================

    /**
     * Walk from {@code node} toward the root, restoring the AVL
     * invariant at every ancestor whose balance factor has
     * left the set {−1, 0, 1}.
     *
     * <ul>
     *   <li>After an <b>insert</b>, at most one trinode
     *       restructure is performed (the repaired subtree
     *       regains its original height, so no ancestor above
     *       is affected).</li>
     *   <li>After a <b>delete</b>, a rotation may shorten the
     *       subtree, unbalancing the grandparent — the walk
     *       therefore continues to the root, potentially
     *       performing O(log n) rotations.</li>
     * </ul>
     */
    private void rebalance(TreeNode<T> node) {
        while (node != null) {
            updateHeight(node);
            int bf = balanceFactor(node);

            if (bf > 1) {
                // ── left-heavy subtree ─────────────────
                if (balanceFactor(node.getLeft()) < 0) {
                    // Left-Right case: double rotation
                    rotateLeft(node.getLeft());
                }
                // Left-Left case (or Left-Right after first rotation)
                rotateRight(node);
                // node has moved down; new subtree root is
                // the node that was rotated up.
                node = node.getParent();

            } else if (bf < -1) {
                // ── right-heavy subtree ────────────────
                if (balanceFactor(node.getRight()) > 0) {
                    // Right-Left case: double rotation
                    rotateRight(node.getRight());
                }
                // Right-Right case (or Right-Left after first rotation)
                rotateLeft(node);
                node = node.getParent();
            }

            // refresh height after any rotation, then ascend
            if (node != null) updateHeight(node);
            node = (node != null) ? node.getParent() : null;
        }
    }

    // ================================================================
    //  Rotation overrides — keep heights consistent
    // ================================================================

    /**
     * Right-rotate, then immediately update the stored heights
     * of the two affected nodes (the one that moved down and
     * the one that moved up).
     */
    @Override
    protected void rotateRight(TreeNode<T> x) {
        super.rotateRight(x);
        updateHeight(x);                // x moved down → update first
        updateHeight(x.getParent());    // y moved up   → update second
    }

    /**
     * Left-rotate with the same height bookkeeping.
     */
    @Override
    protected void rotateLeft(TreeNode<T> x) {
        super.rotateLeft(x);
        updateHeight(x);
        updateHeight(x.getParent());
    }

    // ================================================================
    //  Hooks from BinaryTree
    // ================================================================

    /**
     * Called by {@link BinaryTree#insert} after a new leaf is linked
     * into the tree.  Walks from the new leaf to the root, fixing
     * at most one imbalance.
     */
    @Override
    protected void afterInsert(TreeNode<T> node) {
        rebalance(node);
    }

    /**
     * Called by {@link BinaryTree#delete} after a node has been
     * unlinked.  Walks from the parent of the removed node to the
     * root, fixing every imbalance encountered (up to O(log n)
     * rotations in the worst case).
     */
    @Override
    protected void afterDelete(TreeNode<T> node) {
        rebalance(node);
    }
}

