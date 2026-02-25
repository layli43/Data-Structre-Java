package org.example.assignment1.Stack;

public class Student {
    private String studentNumber;
    private String name;

    public Student(String studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return studentNumber;
    }
}
