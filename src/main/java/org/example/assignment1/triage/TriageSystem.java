package org.example.assignment1.triage;

import org.example.assignment1.Queue.LinkedDeque;
import org.example.assignment1.Queue.LinkedQueue;

public class TriageSystem {

    // CRITICAL & NON_URGENT patients share the deque (front = critical side)
    private final LinkedDeque<Patient> priorityWaitingArea = new LinkedDeque<>();

    // URGENT patients have their own FIFO queue
    private final LinkedQueue<Patient> urgentQueue = new LinkedQueue<>();

    // Stack-based discharge history (supports undo + audit)
    private final java.util.ArrayDeque<Patient> dischargeStack = new java.util.ArrayDeque<>();

    // ── Admission ────────────────────────────────────────────────────────────

    /**
     * Admit a patient into the triage system.
     * CRITICAL  → front of Deque  (seen first, O(1))
     * URGENT    → rear  of Queue  (FIFO within tier, O(1))
     * NON_URGENT→ rear  of Deque  (seen last,  O(1))
     */
    public void admit(Patient patient) {
        switch (patient.getPriority()) {
            case CRITICAL:
                priorityWaitingArea.insertFirst(patient);
                System.out.println("CRITICAL admitted to front → " + patient);
                break;
            case URGENT:
                urgentQueue.enqueue(patient);
                System.out.println("URGENT   admitted to queue → " + patient);
                break;
            case NON_URGENT:
                priorityWaitingArea.insertLast(patient);
                System.out.println("NON-URG  admitted to rear  → " + patient);
                break;
        }
    }

    // ── Treatment (Next Patient) ──────────────────────────────────────────────

    /**
     * Retrieve the next patient for treatment using 3-level scheduling:
     *   1. CRITICAL (front of Deque)
     *   2. URGENT   (front of urgent Queue)
     *   3. NON-URGENT (rear of Deque, via removeLast → we reverse: treat from front)
     *
     * Non-urgent are still stored at rear but accessed from the deque front
     * only after all critical slots are empty, ensuring correct ordering.
     */
    public Patient treatNext() {
        Patient patient = null;

        // Level 1: Critical patients (deque front)
        if (!priorityWaitingArea.isEmpty()) {
            patient = priorityWaitingArea.removeFirst();
        }
        // Level 2: Urgent patients (FIFO queue)
        else if (!urgentQueue.isEmpty()) {
            patient = urgentQueue.dequeue();
        }

        if (patient != null) {
            dischargeStack.push(patient);
            System.out.println("Now treating → " + patient);
        } else {
            System.out.println("No patients waiting.");
        }
        return patient;
    }

    // ── Undo Last Discharge ───────────────────────────────────────────────────

    /**
     * Undo the last discharge: pop from history stack and re-admit the patient
     * at the FRONT of the deque (they were already being treated — give priority).
     * This models a real scenario: wrong patient called, or premature discharge.
     */
    public Patient undoLastDischarge() {
        if (dischargeStack.isEmpty()) {
            System.out.println("No discharge to undo.");
            return null;
        }
        Patient patient = dischargeStack.pop();
        priorityWaitingArea.insertFirst(patient);
        System.out.println("Undo discharge — patient returned to front → " + patient);
        return patient;
    }

    // ── Audit / Reporting ─────────────────────────────────────────────────────

    /** Print full discharge history from the stack (LIFO order). */
    public void printDischargeHistory() {
        System.out.println("\n═══ Discharge History (most recent first) ═══");
        if (dischargeStack.isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        int rank = 1;
        for (Patient p : dischargeStack) {
            System.out.printf("  %2d. %s%n", rank++, p);
        }
    }

    /** Print the current state of all waiting areas. */
    public void printWaitingAreas() {
        System.out.println("\n═══ Current Waiting Areas ═══");
        System.out.println("  Priority Deque  (size=" + priorityWaitingArea.size() + "): " + priorityWaitingArea);
        System.out.println("  Urgent  Queue   (size=" + urgentQueue.size()          + "): " + urgentQueue);
    }

    /** Total patients still waiting. */
    public int totalWaiting() {
        return priorityWaitingArea.size() + urgentQueue.size();
    }
}