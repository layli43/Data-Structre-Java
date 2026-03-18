package org.example.assignment2.gradebook;

import org.example.assignment2.BinaryTree;
import org.example.structres.Position;

public class TreePrinter {
    public static <T> void print(BinaryTree<T> tree) {
        if (tree.isEmpty()) {
            System.out.println("  (empty tree)");
            return;
        }
        printNode(tree, tree.root(), "", true, true);
    }

    private static <T> void printNode(BinaryTree<T> tree,
                                      Position<T> pos,
                                      String prefix,
                                      boolean isRight,
                                      boolean isRoot) {
        if (pos == null) return;

        // print right subtree first (appears on top)
        if (tree.hasRight(pos)) {
            String childPrefix = prefix + (isRoot ? "    " : (isRight ? "    " : "│   "));
            printNode(tree, tree.right(pos), childPrefix, true, false);
        }

        // print this node
        if (isRoot) {
            System.out.println(prefix + " " + label(pos));
        } else {
            System.out.println(prefix + (isRight ? "┌── " : "└── ") + label(pos));
        }

        // print left subtree (appears on bottom)
        if (tree.hasLeft(pos)) {
            String childPrefix = prefix + (isRoot ? "    " : (isRight ? "│   " : "    "));
            printNode(tree, tree.left(pos), childPrefix, false, false);
        }
    }
    private static <T> String label(Position<T> pos) {
        return String.valueOf(pos.element());
    }
}
