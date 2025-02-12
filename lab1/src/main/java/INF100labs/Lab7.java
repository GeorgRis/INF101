package INF100labs;

import java.util.ArrayList;

/**
 * Implement the methods removeRow and allRowsAndColsAreEqualSum.
 * These programming tasks was part of lab7 in INF100 fall 2022/2023.
 */
public class Lab7 {
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs
    }

    public static void removeRow(ArrayList<ArrayList<Integer>> grid, int row) {
        grid.remove(row);
    }

    /** Codium suggestion: Brukt for forståelse.
     * Checks if all rows and columns in the 2D array (grid) has the same sum.
     * The sum of the first row is used as the basis for comparison.
     * @param grid the 2D array to check.
     * @return true if all rows and columns have the same sum, false otherwise.
     */
    public static boolean allRowsAndColsAreEqualSum(ArrayList<ArrayList<Integer>> grid) {
        int firstRowSum = grid.get(0).stream().mapToInt(Integer::intValue).sum();
        for (ArrayList<Integer> row : grid) {
            if (row.stream().mapToInt(Integer::intValue).sum() != firstRowSum) {
                return false;
            }
        }

        for (int col = 0; col < grid.get(0).size(); col++) {
            int colSum = 0;
            for (ArrayList<Integer> row : grid) {
                colSum += row.get(col);
            }
            if (colSum != firstRowSum) {
                return false;
            }
        }

        return true;
    }
}