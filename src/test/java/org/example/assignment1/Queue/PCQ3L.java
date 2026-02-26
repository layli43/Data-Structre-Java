package org.example.assignment1.Queue;

public class PCQ3L {
    public static void main(String[] args) {
        LinkedQueue<String> q = new LinkedQueue<>();
        String resPoped = "";
        String resStored = "";

        q.enqueue("Ireland");
        resPoped += q.dequeue() + " ";

        q.enqueue("England");
        resPoped += q.dequeue() + " ";

        q.enqueue("Wales");
        resPoped += q.dequeue() + " ";

        q.enqueue("Scotland");
        resPoped += q.dequeue() + " ";

        q.enqueue("France");
        q.enqueue("Germany");

        System.out.println("Poped result: " + resPoped.trim());
        System.out.println(q);
        while(!q.isEmpty()) {
            resStored += q.dequeue() + " ";
        }

        System.out.println("Stored result: " + resStored.trim());
    }
}
