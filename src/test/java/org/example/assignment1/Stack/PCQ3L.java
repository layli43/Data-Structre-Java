package org.example.assignment1.Stack;

public class PCQ3L {
    public static void main(String[] args) {
        LinkedStack<String> s = new LinkedStack<>();
        String resPoped = "";
        String resStored = "";

        s.push("Ireland");
        resPoped += s.pop() + " ";

        s.push("England");
        resPoped += s.pop() + " ";

        s.push("Wales");
        resPoped += s.pop() + " ";

        s.push("Scotland");
        resPoped += s.pop() + " ";

        s.push("France");
        s.push("Germany");

        System.out.println("Poped result: " + resPoped.trim());

        while(!s.isEmpty()) {
            resStored += s.pop() + " ";
        }
        System.out.println("Stored result: " + resStored.trim());
    }
}
