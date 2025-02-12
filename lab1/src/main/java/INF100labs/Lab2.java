package INF100labs;

import java.util.Arrays;
import java.util.Collections;

/**
 * Implement the methods findLongestWords, isLeapYear and isEvenPositiveInt.
 * These programming tasks was part of lab2 in INF100 fall 2022/2023.
 */
public class Lab2 {
    
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs
    }

    public static void findLongestWords(String word1, String word2, String word3) {
        String[] words = {word1, word2, word3};
        Arrays.sort(words, Collections.reverseOrder((a, b) -> Integer.compare(a.length(), b.length())));
        
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isEvenPositiveInt(int num) {
        return num > 0 && num % 2 == 0;
    }
}