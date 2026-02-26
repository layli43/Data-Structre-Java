package org.example.assignment1.triage;

public class ERTriageDemo {

    public static void main(String[] args) {

        TriageSystem er = new TriageSystem();

        separator("PHASE 1 — Admitting Patients (mixed arrival order)");

        // Non-urgent arrives first — goes to rear of deque
        er.admit(new Patient("Alice Brown",   Patient.Priority.NON_URGENT, "Mild headache"));
        er.admit(new Patient("Bob Smith",     Patient.Priority.URGENT,     "Broken arm"));
        er.admit(new Patient("Carol White",   Patient.Priority.CRITICAL,   "Cardiac arrest"));
        er.admit(new Patient("David Lee",     Patient.Priority.URGENT,     "Severe burn"));
        er.admit(new Patient("Eva Martinez",  Patient.Priority.NON_URGENT, "Sprained ankle"));
        er.admit(new Patient("Frank Johnson", Patient.Priority.CRITICAL,   "Stroke symptoms"));
        er.admit(new Patient("Grace Kim",     Patient.Priority.URGENT,     "Appendicitis"));

        er.printWaitingAreas();

        separator("PHASE 2 — Treating Patients (priority order)");
        // Expected order: Frank (CRITICAL, admitted last → front),
        //                 Carol (CRITICAL), Bob (URGENT), David (URGENT),
        //                 Grace (URGENT), then non-urgent from deque
        for (int i = 0; i < 5; i++) {
            er.treatNext();
        }

        er.printWaitingAreas();

        separator("PHASE 3 — Undo Last Discharge (Stack in action)");
        // Simulates: nurse realizes Grace was discharged prematurely
        er.undoLastDischarge();
        er.printWaitingAreas();

        separator("PHASE 4 — New Critical patient arrives mid-shift");
        er.admit(new Patient("Henry Cruz", Patient.Priority.CRITICAL, "Severe trauma"));

        // Henry (CRITICAL) should jump to front
        er.treatNext();
        er.treatNext();
        er.treatNext();

        separator("PHASE 5 — Discharge History Audit (Stack LIFO)");
        er.printDischargeHistory();

        separator("FINAL — Drain remaining patients");
        while (er.totalWaiting() > 0) {
            er.treatNext();
        }
        er.printDischargeHistory();

        System.out.println("\nShift complete. All patients processed.");
    }

    private static void separator(String title) {
        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.printf( "│ %-47s │%n", title);
        System.out.println("└─────────────────────────────────────────────────┘");
    }
}