package org.example.assignment1.Stack;

public class PCQ1L {
    public static void main(String[] args) {
        LinkedStack<Integer> s = new LinkedStack<>();
        int resPoped = 0, resStored = 0;

        s.push(15);
        s.push(20);
        s.push(5);

        resPoped += s.pop().intValue();
        System.out.println(s);

        s.push(17);
        s.push(12);

        resPoped += s.pop().intValue();
        System.out.println(s);

        s.push(7);
        s.push(13);
        s.push(2);

        System.out.println("Poped result: " + resPoped);
        System.out.println(s);
        while(!s.isEmpty()) {
            resStored += s.pop().intValue();
        }

        System.out.println("Stored result: " + resStored);
    }
}
