package org.example.assignment2;

import java.util.Comparator;

public class BinaryTreeTests {

    public static void main(String[] args) {

        System.out.println("Test 1: Insert + Inorder Traversal");
        testInsertAndTraversal();

        System.out.println("\nTest 2: Search");
        testSearch();

        System.out.println("\nTest 3: Duplicate Insert");
        testDuplicateInsert();

        System.out.println("\nTest 4: Delete Leaf");
        testDeleteLeaf();

        System.out.println("\nTest 5: Delete One Child");
        testDeleteOneChild();

        System.out.println("\nTest 6: Delete Two Children");
        testDeleteTwoChildren();

        System.out.println("\nTest 7: Height");
        testHeight();

        System.out.println("\nTest 8: Empty Tree");
        testEmptyTree();

        System.out.println("\nTest 9: Root Deletion");
        testRootDeletion();
    }

    // 1. Insert + Traversal
    public static void testInsertAndTraversal() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.print("Inorder: ");
        var it = tree.elements();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    // 2. Search
    public static void testSearch() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        System.out.println("Contains 5: " + tree.contains(5));
        System.out.println("Contains 20: " + tree.contains(20));

        if (tree.find(10) != null)
            System.out.println("Find 10: " + tree.find(10).element());
    }

    // 3. Duplicate insert
    public static void testDuplicateInsert() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(10);
        tree.insert(10);

        System.out.println("Size: " + tree.size());
    }

    // 4. Delete leaf
    public static void testDeleteLeaf() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(5);

        System.out.print("After deleting 5: ");
        var it = tree.elements();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }

    // 5. Delete node with one child
    public static void testDeleteOneChild() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(10);
        tree.insert(5);
        tree.insert(2);

        tree.delete(5);

        System.out.print("After deleting 5: ");
        var it = tree.elements();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }

    // 6. Delete node with two children
    public static void testDeleteTwoChildren() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        tree.delete(50);

        System.out.print("After deleting 50: ");
        var it = tree.elements();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }

    // 7. Height
    public static void testHeight() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);

        System.out.println("Height: " + tree.height());
    }

    // 8. Empty tree
    public static void testEmptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        System.out.println("Is empty: " + tree.isEmpty());
        System.out.println("Delete from empty: " + tree.delete(10));
    }

    // 9. Root deletion cases
    public static void testRootDeletion() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

        tree.insert(10);
        tree.delete(10);

        System.out.println("After deleting root, isEmpty: " + tree.isEmpty());

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);

        tree.delete(20);

        System.out.print("After deleting root with children: ");
        var it = tree.elements();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }
}
