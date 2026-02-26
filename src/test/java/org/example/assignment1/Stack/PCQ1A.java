package org.example.assignment1.Stack;

public class PCQ1A {
    public static void main(String[] args) {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        int resPoped = 0, resStored = 0;
        s.push(15);
        s.push(20);
        s.push(5);

        resPoped += s.pop();

        s.push(17);
        s.push(12);

        resPoped += s.pop();

        s.push(7);
        s.push(13);
        s.push(2);

        System.out.println("Poped result: "+resPoped);

        while(!s.isEmpty()) {
            resStored += s.pop();
        }
        System.out.println(s);
        System.out.println("Stored result: "+resStored);
    }
}
