package org.example.assignment1.Queue;

public class PCQ1L {
    public static void main(String[] args) {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        int resPoped = 0, resStored = 0;

        q.enqueue(15);
        q.enqueue(20);
        q.enqueue(5);

        resPoped += q.dequeue();
        System.out.println(q);

        q.enqueue(17);
        q.enqueue(12);

        resPoped += q.dequeue();
        System.out.println(q);

        q.enqueue(7);
        q.enqueue(13);
        q.enqueue(2);

        System.out.println("Poped result: " + resPoped);
        System.out.println(q);
        while(!q.isEmpty()) {
            resStored += q.dequeue();
        }
        System.out.println("Stored result: " + resStored);
    }
}
