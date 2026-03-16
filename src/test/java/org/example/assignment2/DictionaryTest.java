package org.example.assignment2;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    @Test
    void testSingleWordPrediction() {

        Dictionary dict = new Dictionary();

        dict.insert("fun");

        List<Integer> keys = Arrays.asList(3,8,6);

        List<String> result = dict.findWord(keys);

        assertTrue(result.contains("fun"));
    }

    @Test
    void testMultipleWordsSamePrefix() {

        Dictionary dict = new Dictionary();

        dict.insert("good");
        dict.insert("god");

        List<Integer> keys = Arrays.asList(4,6);

        List<String> result = dict.findWord(keys);

        assertTrue(result.contains("go"));
    }

    @Test
    void testWordFragments() {

        Dictionary dict = new Dictionary();

        dict.insert("hello");

        List<Integer> keys = Arrays.asList(4);

        List<String> result = dict.findWord(keys);

        assertTrue(result.contains("h"));
    }

    @Test
    void testMultipleInsertions() {

        Dictionary dict = new Dictionary();

        dict.insert("fun");
        dict.insert("good");
        dict.insert("god");
        dict.insert("hello");

        List<Integer> keys = Arrays.asList(4,3,5,5,6);

        List<String> result = dict.findWord(keys);

        assertTrue(result.contains("hello"));
    }

    @Test
    void testWordNotFound() {

        Dictionary dict = new Dictionary();

        dict.insert("fun");

        List<Integer> keys = Arrays.asList(9,9,9);

        List<String> result = dict.findWord(keys);

        assertNull(result);
    }
}