package org.example.assignment2;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Node {

    int key;
    Map<Integer, Node> children;
    List<String> words;

    public Node(int key) {

        this.key = key;
        children = new HashMap<>();
        words = new ArrayList<>();
    }
}