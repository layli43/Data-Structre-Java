package org.example.assignment1.Stack;

public class PCQ4A {
    public static void main(String[] args) {
        ArrayStack<Student> s = new ArrayStack<>(10);
        String resPoped = "";
        String resStored = "";

        s.push(new Student("01020304", "Rem Templin"));
        s.push(new Student("01020306", "Joe Carthy"));
        s.push(new Student("01020308", "Michela Bertolotto"));
        s.push(new Student("01020310", "Henry McLoughlin"));
        s.push(new Student("01020305", "David Leonard"));
        s.push(new Student("01020307", "John Dunnion"));
        s.push(new Student("01020309", "Fintan Costello"));
        s.push(new Student("01020311", "Damian Dalton"));

        resPoped += s.pop().getName() + " ";
        resPoped += s.pop().getName() + " ";
        resPoped += s.pop().getName() + " ";

        System.out.println("Poped result: " + resPoped.trim());

        while(!s.isEmpty()) {
            resStored += s.pop().getName() + " ";
        }
        System.out.println(s);
        System.out.println("Stored result: " + resStored.trim());
    }
}
