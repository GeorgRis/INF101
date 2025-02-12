package INF100labs;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Implement the methods removeThrees, uniqueValues and addList.
 * These programming tasks was part of lab5 in INF100 fall 2022/2023.
 */
public class Lab5 {
    
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs
    }

    public static ArrayList<Integer> multipliedWithTwo(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer num : list) {
            result.add(num * 2);
        }
        return result;
    }

    public static ArrayList<Integer> removeThrees(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer num : list) {
            if (num != 3) {
                result.add(num);
            }
        }
        return result;
    }

    public static ArrayList<Integer> uniqueValues(ArrayList<Integer> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    public static void addList(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i) + b.get(i));
        }
    }
}