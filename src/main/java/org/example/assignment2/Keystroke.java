package org.example.assignment2;

import java.util.ArrayList;
import java.util.List;

public class Keystroke {

    private int key;
    private List<String> words;

    public Keystroke(int key) {
        this.key = key;
        this.words = new ArrayList<>();
    }

    public void addWord(String word) {

        if (!words.contains(word)) {
            words.add(word);
        }
    }

    public List<String> getWords() {
        return words;
    }

    public int getKey() {
        return key;
    }
}