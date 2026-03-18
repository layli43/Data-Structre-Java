package org.example.assignment2.gradebook;

/**
 * Immutable student record keyed by a unique integer ID.
 */
public class Student {

    private final int    id;
    private final String name;
    private final double grade;   // 0.0 – 100.0

    public Student(int id, String name, double grade) {
        this.id    = id;
        this.name  = name;
        this.grade = grade;
    }

    public int    getId()    { return id; }
    public String getName()  { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return String.format("[%04d] %-18s %.1f", id, name, grade);
    }
}

