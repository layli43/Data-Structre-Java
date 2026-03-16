package org.example.assignment2;

import java.util.*;

public class Dictionary {

    private Node root;

    private static Map<Character, Integer> characterMap = new HashMap<>();

    static {

        characterMap.put('a',2);
        characterMap.put('b',2);
        characterMap.put('c',2);

        characterMap.put('d',3);
        characterMap.put('e',3);
        characterMap.put('f',3);

        characterMap.put('g',4);
        characterMap.put('h',4);
        characterMap.put('i',4);

        characterMap.put('j',5);
        characterMap.put('k',5);
        characterMap.put('l',5);

        characterMap.put('m',6);
        characterMap.put('n',6);
        characterMap.put('o',6);

        characterMap.put('p',7);
        characterMap.put('q',7);
        characterMap.put('r',7);
        characterMap.put('s',7);

        characterMap.put('t',8);
        characterMap.put('u',8);
        characterMap.put('v',8);

        characterMap.put('w',9);
        characterMap.put('x',9);
        characterMap.put('y',9);
        characterMap.put('z',9);
    }

    public Dictionary() {
        root = new Node(0);
    }

    public void insert(String word) {

        Node current = root;

        String fragment = "";

        for (char c : word.toCharArray()) {

            int key = characterMap.get(c);

            fragment += c;

            current.children.putIfAbsent(key, new Node(key));

            current = current.children.get(key);

            if (!current.words.contains(fragment)) {
                current.words.add(fragment);
            }
        }
    }

    public List<String> findWord(List<Integer> keys) {

        Node current = root;

        for (int key : keys) {

            if (!current.children.containsKey(key)) {
                return null;
            }

            current = current.children.get(key);
        }

        return current.words;
    }
}