package org.example.assignment1.Queue;

public class PCQ7L {
    public static void main(String[] args) {
        LinkedDeque<String> d = new LinkedDeque<>();
        String resRemoved = "";
        String resStored = "";

        d.insertFirst("Ireland");
        resRemoved += d.removeLast() + " ";

        d.insertLast("England");
        resRemoved += d.removeFirst() + " ";

        d.insertLast("Wales");
        d.insertFirst("Scotland");
        d.insertLast("France");

        resRemoved += d.removeFirst() + " ";
        resRemoved += d.removeLast() + " ";

        d.insertLast("Germany");

        System.out.println("Removed countries: " + resRemoved.trim());

        while(!d.isEmpty()) {
            resStored += d.removeFirst() + " ";
        }
        System.out.println("Stored countries: " + resStored.trim());
    }
}
