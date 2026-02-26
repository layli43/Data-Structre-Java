package org.example.assignment1.triage;

/**
 * Represents a patient in the Emergency Room Triage System.
 * Priority levels:
 *   1 = Critical   → inserted at FRONT of Deque (immediate attention)
 *   2 = Urgent     → inserted into standard Queue
 *   3 = Non-Urgent → inserted at REAR of Deque (can wait longer)
 */
public class Patient {
    public enum Priority { CRITICAL, URGENT, NON_URGENT }

    private static int idCounter = 1;

    private final int id;
    private final String name;
    private final Priority priority;
    private final String condition;

    public Patient(String name, Priority priority, String condition) {
        this.id = idCounter++;
        this.name = name;
        this.priority = priority;
        this.condition = condition;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Priority getPriority() { return priority; }
    public String getCondition() { return condition; }

    @Override
    public String toString() {
        return String.format("[#%03d | %-12s | %-10s | %s]",
                id, name, priority, condition);
    }
}
