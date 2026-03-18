package org.example.assignment2;

import java.util.Comparator;

public class AVLTreeAllTests {

    public static void main(String[] args) {

        AvlTree<Integer> tree = new AvlTree<Integer>(Comparator.naturalOrder());

        // Test 1: Basic Insert
        System.out.println("Test 1: Insert");

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        System.out.println("Contains 20: " + tree.contains(20));
        System.out.println("Height: " + tree.height());

        // Test 2: Rotation Check
        System.out.println("\nTest 2: Rotation");

        AvlTree<Integer> tree2 = new AvlTree<Integer>(Comparator.naturalOrder());

        tree2.insert(30);
        tree2.insert(20);
        tree2.insert(10); // should rotate

        System.out.println("Root after rotation: " + tree2.root().element());

        // Test 3: Left-Right Rotation
        System.out.println("\nTest 3: Left-Right Rotation");

        AvlTree<Integer> tree3 = new AvlTree<Integer>(Comparator.naturalOrder());

        tree3.insert(30);
        tree3.insert(10);
        tree3.insert(20);

        System.out.println("Root: " + tree3.root().element());

        // Test 4: Search
        System.out.println("\nTest 4: Search");

        System.out.println("Find 10: " + (tree.contains(10)));
        System.out.println("Find 100: " + (tree.contains(100)));

        // Test 5: Delete
        System.out.println("\nTest 5: Delete");

        tree.delete(20);

        System.out.println("Contains 20 after delete: " + tree.contains(20));
        System.out.println("Height after delete: " + tree.height());

        // Test 6: Stress Test
        System.out.println("\nTest 6: Stress Test");

        AvlTree<Integer> bigTree = new AvlTree<Integer>(Comparator.naturalOrder());

        for (int i = 1; i <= 1000; i++) {
            bigTree.insert(i);
        }

        System.out.println("Nodes: 1000");
        System.out.println("Height: " + bigTree.height());
    }
}