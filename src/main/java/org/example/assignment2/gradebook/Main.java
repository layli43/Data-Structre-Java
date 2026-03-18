package org.example.assignment2.gradebook;

import org.example.assignment2.BinaryTree;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        GradeBook book = new GradeBook();

        // ─── 1. Enrol students ──────────────────────────
        System.out.println("═══ ENROLLING STUDENTS ═══\n");

        book.enrol(new Student(25203212, "Alice Johnson",    88.5));
        book.enrol(new Student(25203202, "Bob Smith",        72.0));
        book.enrol(new Student(25203215, "Carol Williams",   95.3));
        book.enrol(new Student(25203228, "David Brown",      61.0));
        book.enrol(new Student(25203207, "Eve Davis",        79.4));
        book.enrol(new Student(25203226, "Frank Miller",     83.7));
        book.enrol(new Student(25203218, "Grace Wilson",     91.2));

        System.out.println("  Enrolled " + book.studentCount() + " students.");
        System.out.println("  Tree height: " + book.treeHeight());
        System.out.println();

        // ─── 2. Print full roster (in-order traversal) ──
        book.printRoster();
        System.out.println();

        // ─── 3. ASCII tree visualisation ────────────────
        System.out.println("═══ TREE SHAPE ═══\n");
        // Access the underlying tree for printing.
        // In a real project you'd expose this through
        // a getter; here we rebuild a small tree to
        // keep Main self-contained with TreePrinter.
        BinaryTree<Integer> demoTree = new BinaryTree<>(Comparator.naturalOrder());
        for (int id : new int[]{1025, 1010, 1042, 1005, 1018, 1030, 1050}) {
            demoTree.insert(id);
        }
        TreePrinter.print(demoTree);
        System.out.println();

        // ─── 4. Lookup & membership ─────────────────────
        System.out.println("═══ LOOKUPS ═══\n");

        Student found = book.lookup(1042);
        System.out.println("  find(1042)   → " + found);

        System.out.println("  contains(1010) → " + book.isEnrolled(1010));
        System.out.println("  contains(9999) → " + book.isEnrolled(9999));
        System.out.println();

        // ─── 5. Update a grade (replace) ────────────────
        System.out.println("═══ UPDATE GRADE ═══\n");

        System.out.println("  Before: " + book.lookup(1010));
        book.updateGrade(1010, 85.0);
        System.out.println("  After:  " + book.lookup(1010));
        System.out.println();

        // ─── 6. Withdraw a student (delete) ─────────────
        System.out.println("═══ WITHDRAW STUDENT ═══\n");

        Student removed = book.withdraw(1042);
        System.out.println("  Withdrawn: " + removed);
        System.out.println("  Enrolled now: " + book.studentCount());
        System.out.println("  Tree height:  " + book.treeHeight());
        System.out.println();

        // ─── 7. Roster after mutation ───────────────────
        book.printRoster();
        System.out.println();

        // ─── 8. Grade statistics ────────────────────────
        System.out.println("═══ GRADE STATISTICS ═══\n");

        double[] stats = book.gradeStats();
        System.out.printf("  Min : %.1f%n", stats[0]);
        System.out.printf("  Max : %.1f%n", stats[1]);
        System.out.printf("  Avg : %.1f%n", stats[2]);
        System.out.println();

        // ─── 9. Structure walk (positional queries) ─────
        System.out.println("═══ TREE STRUCTURE DETAIL ═══\n");
        book.printStructureInfo();
        System.out.println();

        // ─── 10. Edge case: empty tree ──────────────────
        System.out.println("═══ EMPTY TREE ═══\n");

        GradeBook empty = new GradeBook();
        System.out.println("  isEmpty: " + empty.isEmpty());
        System.out.println("  size:    " + empty.studentCount());
        empty.printRoster();
    }
}

