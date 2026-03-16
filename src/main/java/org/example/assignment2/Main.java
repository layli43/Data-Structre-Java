package org.example.assignment2;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Dictionary dict = new Dictionary();

        dict.insert("fun");
        dict.insert("good");
        dict.insert("god");
        dict.insert("hello");

        List<Integer> keys = Arrays.asList(3,8,6);

        System.out.println(dict.findWord(keys));
    }
}