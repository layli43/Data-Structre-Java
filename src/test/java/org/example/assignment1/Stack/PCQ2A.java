package org.example.assignment1.Stack;

public class PCQ2A {
    public static void main(String[] args) {
        ArrayStack<Character> s = new ArrayStack<>(10);
        String resPoped = "";
        String resStored = "";

        s.push('e');
        s.push('s');
        s.push('c');

        resPoped += s.pop();

        s.push('u');
        s.push('a');

        resPoped += s.pop();

        s.push('o');
        s.push('t');

        resPoped += s.pop();

        s.push('h');

        System.out.println("Poped result: " + resPoped);

        while(!s.isEmpty()) {
            resStored += s.pop();
        }
        System.out.println("Stored result: " + resStored);
    }
}
