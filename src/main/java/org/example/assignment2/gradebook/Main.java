package org.example.assignment2.gradebook;

import org.example.assignment2.BinaryTree;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        GradeBook book = new GradeBook();

        //Enrol students
        System.out.println("Enrolling students\n");

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

        //Print full roster (in-order traversal)
        book.printRoster();

        //Lookup & membership
        System.out.println("Lookups:\n");

        Student found = book.lookup(25203228);
        System.out.println("  find(25203228)   → " + found);

        System.out.println("  contains(25203228) → " + book.isEnrolled(25203228));
        System.out.println("  contains(9999) → " + book.isEnrolled(9999));
        System.out.println();

        //Update a grade (replace)
        System.out.println("Update grade:\n");

        System.out.println("Before: " + book.lookup(25203228));
        book.updateGrade(25203228, 85.0);
        System.out.println("  After:  " + book.lookup(25203228));
        System.out.println();

        //Withdraw a student
        System.out.println("Withdraw student:\n");

        Student removed = book.withdraw(25203228);
        System.out.println("  Withdrawn: " + removed);
        System.out.println("  Enrolled now: " + book.studentCount());
        System.out.println("  Tree height:  " + book.treeHeight());
        System.out.println();

        //Roster after mutation
        System.out.println("Roster after mutation:\n");
        book.printRoster();
        System.out.println();

        //Grade statistics
        System.out.println("Grade statistics:\n");

        double[] stats = book.gradeStats();
        System.out.printf("  Min : %.1f%n", stats[0]);
        System.out.printf("  Max : %.1f%n", stats[1]);
        System.out.printf("  Avg : %.1f%n", stats[2]);
        System.out.println();

        //Structure walk (positional queries)
        System.out.println("Tree structure detail:\n");
        book.printStructureInfo();
        System.out.println();

        //Edge case: empty tree
        System.out.println("Empty tree:\n");

        GradeBook empty = new GradeBook();
        System.out.println("  isEmpty: " + empty.isEmpty());
        System.out.println("  size:    " + empty.studentCount());
        empty.printRoster();
    }
}

