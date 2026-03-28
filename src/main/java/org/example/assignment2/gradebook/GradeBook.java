package org.example.assignment2.gradebook;

import org.example.assignment2.BinaryTree;
import org.example.structures.Iterator;
import org.example.structures.Position;

import java.util.Comparator;

public class GradeBook {

    /** Students ordered by ID. */
    private final BinaryTree<Student> tree;

    public GradeBook() {
        // Compare students by their integer ID.
        Comparator<Student> byId = Comparator.comparingInt(Student::getId);
        this.tree = new BinaryTree<>(byId);
    }

    // ── Mutations ────────────────────────────────────────

    /** Enrol a student.  If the ID already exists the record is overwritten. */
    public void enrol(Student s) {
        tree.insert(s);
    }

    /** Withdraw a student by ID.  Returns the removed record, or null. */
    public Student withdraw(int id) {
        return tree.delete(stub(id));
    }

    /** Update the grade for an existing student (uses replace). */
    public boolean updateGrade(int id, double newGrade) {
        Position<Student> pos = tree.find(stub(id));
        if (pos == null) return false;
        Student old = pos.element();
        tree.replace(pos, new Student(old.getId(), old.getName(), newGrade));
        return true;
    }

    // ── Queries ──────────────────────────────────────────

    /** Look up a student by ID. */
    public Student lookup(int id) {
        Position<Student> pos = tree.find(stub(id));
        return (pos != null) ? pos.element() : null;
    }

    public boolean isEnrolled(int id) {
        return tree.contains(stub(id));
    }

    public int    studentCount() { return tree.size(); }
    public boolean isEmpty()     { return tree.isEmpty(); }
    public int    treeHeight()   { return tree.height(); }

    // ── Reports (traverse the tree) ─────────────────────

    /**
     * Print every student in ascending ID order.
     * Uses the elements() iterator from AbstractTree.
     */
    public void printRoster() {
        System.out.println("           STUDENT ROSTER               ");
        System.out.printf( "   Enrolled: %-4d   Tree height: %-4d    %n",
                tree.size(), tree.height());

        if (tree.isEmpty()) {
            System.out.println("   (no students enrolled)                ");
        } else {
            Iterator<Student> it = tree.elements();
            while (it.hasNext()) {
                System.out.println("  " + it.next() + "    ");
            }
        }
    }

    /**
     * Compute class statistics by iterating all elements.
     * Returns {min, max, average} grades.
     */
    public double[] gradeStats() {
        if (tree.isEmpty()) return new double[]{0, 0, 0};

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;
        int    count = 0;

        Iterator<Student> it = tree.elements();
        while (it.hasNext()) {
            double g = it.next().getGrade();
            if (g < min) min = g;
            if (g > max) max = g;
            sum += g;
            count++;
        }
        return new double[]{ min, max, sum / count };
    }

    /**
     * Walk the tree using positional methods to show the
     * structure: root, parent, children, internal/external.
     */
    public void printStructureInfo() {
        if (tree.isEmpty()) {
            System.out.println("  (empty tree)");
            return;
        }

        System.out.println("  Root  : " + tree.root().element());
        System.out.println();

        Iterator<Position<Student>> it = tree.positions();
        while (it.hasNext()) {
            Position<Student> pos = it.next();
            String kind = tree.isExternal(pos) ? "LEAF" : "INTERNAL";
            String par  = tree.isRoot(pos) ? "(root)"
                    : pos.element().toString();

            System.out.printf("  %-8s %s", kind, pos.element());
            if (!tree.isRoot(pos)) {
                System.out.printf("  parent→%s",
                        tree.parent(pos).element().getId() + "");
            }

            // show children
            if (tree.isInternal(pos)) {
                StringBuilder kids = new StringBuilder("  children→[");
                Iterator<Position<Student>> ch = tree.children(pos);
                boolean first = true;
                while (ch.hasNext()) {
                    if (!first) kids.append(", ");
                    kids.append(ch.next().element().getId());
                    first = false;
                }
                kids.append("]");
                System.out.print(kids);
            }
            System.out.println();
        }
    }

    // ── Helper ───────────────────────────────────────────

    /** Create a dummy Student with just an ID, for lookup/delete by key. */
    private Student stub(int id) {
        return new Student(id, "", 0);
    }
}
