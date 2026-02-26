package org.example.assignment1.Stack;

public class PCQ2L {
    public static void main(String[] args) {
        LinkedStack<Character> s = new LinkedStack<>();
        String resPoped = "";
        String resStored = "";

        s.push('e');
        s.push('s');
        s.push('c');

        resPoped += s.pop();
        System.out.println(s);

        s.push('u');
        s.push('a');

        resPoped += s.pop();
        System.out.println(s);

        s.push('o');
        s.push('t');

        resPoped += s.pop();
        System.out.println(s);

        s.push('h');

        System.out.println("Poped result: " + resPoped);
        System.out.println(s);
        while(!s.isEmpty()) {
            resStored += s.pop();
        }

        System.out.println("Stored result: " + resStored);
    }
}
