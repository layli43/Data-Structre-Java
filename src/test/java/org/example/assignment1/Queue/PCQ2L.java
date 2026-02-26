package org.example.assignment1.Queue;

public class PCQ2L {
    public static void main(String[] args) {
        LinkedQueue<Character> q = new LinkedQueue<>();
        String resPoped = "";
        String resStored = "";

        q.enqueue('c');
        q.enqueue('a');
        q.enqueue('t');

        resPoped += q.dequeue();

        q.enqueue('h');
        q.enqueue('o');

        resPoped += q.dequeue();

        q.enqueue('u');
        q.enqueue('s');
        q.enqueue('e');

        System.out.println("Poped result: " + resPoped);

        while(!q.isEmpty()) {
            resStored += q.dequeue();
        }
        System.out.println(q);
        System.out.println("Stored result: " + resStored);
    }
}
